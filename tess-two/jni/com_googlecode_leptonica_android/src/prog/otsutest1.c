/*====================================================================*
 -  Copyright (C) 2001 Leptonica.  All rights reserved.
 -  This software is distributed in the hope that it will be
 -  useful, but with NO WARRANTY OF ANY KIND.
 -  No author or distributor accepts responsibility to anyone for the
 -  consequences of using this software, or for whether it serves any
 -  particular purpose or works at all, unless he or she says so in
 -  writing.  Everyone is granted permission to copy, modify and
 -  redistribute this source code, for commercial or non-commercial
 -  purposes, with the following restrictions: (1) the origin of this
 -  source code must not be misrepresented; (2) modified versions must
 -  be plainly marked as such; and (3) this notice may not be removed
 -  or altered from any source or modified source distribution.
 *====================================================================*/

/*
 *   otsutest1.c
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "allheaders.h"

static const l_int32 NTests = 5;
static const l_int32 gaussmean1[5] = {20, 40, 60, 80, 60};
static const l_int32 gaussstdev1[5] = {10, 20, 20, 20, 30};
static const l_int32 gaussmean2[5] = {220, 200, 140, 180, 150};
static const l_int32 gaussstdev2[5] = {15, 20, 40, 20, 30};
static const l_float32 gaussfract1[5] = {0.2, 0.3, 0.1, 0.5, 0.3};
static char  buf[256];

static l_int32  GenerateSplitPlot(l_int32 i);
static NUMA *MakeGaussian(l_int32 mean, l_int32 stdev, l_float32 fract);



main(int    argc,
     char **argv)
{
l_int32  i;
PIX     *pix;
PIXA    *pixa;

    for (i = 0; i < NTests; i++)
        GenerateSplitPlot(i);

       /* Read the results back in ...  */
    pixa = pixaCreate(0);
    for (i = 0; i < NTests; i++) {
        sprintf(buf, "/tmp/junkplot.%d.png", i);
	pix = pixRead(buf);
	pixSaveTiled(pix, pixa, 1, 1, 25, 32);
	pixDestroy(&pix);
        sprintf(buf, "/tmp/junkplots.%d.png", i);
	pix = pixRead(buf);
	pixSaveTiled(pix, pixa, 1, 0, 25, 32);
	pixDestroy(&pix);
    }

        /* ... and save into a tiled pix  */
    pix = pixaDisplay(pixa, 0, 0);
    pixWrite("/tmp/junkotsuplot.png", pix, IFF_PNG);
    pixDisplay(pix, 100, 100);
    pixaDestroy(&pixa);
    pixDestroy(&pix);
    return 0;
}


static l_int32
GenerateSplitPlot(l_int32  i)
{
char       title[256];
l_int32    split;
l_float32  ave1, ave2, num1, num2, maxnum, maxscore;
GPLOT     *gplot;
NUMA      *na1, *na2, *nascore, *nax, *nay;
PIX       *pixs, *pixd;

        /* Generate */
    na1 = MakeGaussian(gaussmean1[i], gaussstdev1[i], gaussfract1[i]);
    na2 = MakeGaussian(gaussmean2[i], gaussstdev1[i], 1.0 - gaussfract1[i]);
    numaArithOp(na1, na1, na2, L_ARITH_ADD);

        /* Otsu splitting */
    numaSplitDistribution(na1, 0.08, &split, &ave1, &ave2, &num1, &num2,
                          &nascore);
    fprintf(stderr, "split = %d, ave1 = %6.1f, ave2 = %6.1f\n",
            split, ave1, ave2);
    fprintf(stderr, "num1 = %8.0f, num2 = %8.0f\n", num1, num2);

        /* Prepare for plotting a vertical line at the split point */
    nax = numaMakeConstant(split, 2);
    numaGetMax(na1, &maxnum, NULL);
    nay = numaMakeConstant(0, 2);
    numaReplaceNumber(nay, 1, (l_int32)(0.5 * maxnum));

        /* Plot the input histogram with the split location */
    sprintf(buf, "/tmp/junkplot.%d", i);
    sprintf(title, "Plot %d", i);
    gplot = gplotCreate(buf, GPLOT_PNG,
                        "Histogram: mixture of 2 gaussians",
                        "Grayscale value", "Number of pixels");
    gplotAddPlot(gplot, NULL, na1, GPLOT_LINES, title);
    gplotAddPlot(gplot, nax, nay, GPLOT_LINES, NULL);
    gplotMakeOutput(gplot);
    gplotDestroy(&gplot);
    numaDestroy(&na1);
    numaDestroy(&na2);

        /* Plot the score function */
    sprintf(buf, "/tmp/junkplots.%d", i);
    sprintf(title, "Plot %d", i);
    gplot = gplotCreate(buf, GPLOT_PNG,
                        "Otsu score function for splitting",
                        "Grayscale value", "Score");
    gplotAddPlot(gplot, NULL, nascore, GPLOT_LINES, title);
    numaGetMax(nascore, &maxscore, NULL);
    numaReplaceNumber(nay, 1, maxscore);
    gplotAddPlot(gplot, nax, nay, GPLOT_LINES, NULL);
    gplotMakeOutput(gplot);
    gplotDestroy(&gplot);
    numaDestroy(&nax);
    numaDestroy(&nay);
    numaDestroy(&nascore);
    return 0;
}


static NUMA *
MakeGaussian(l_int32 mean, l_int32 stdev, l_float32 fract)
{
l_int32    i, total;
l_float32  norm, val;
NUMA      *na;

    na = numaMakeConstant(0.0, 256);
    norm = fract / ((l_float32)stdev * sqrt(2 * 3.14159));
    total = 0;
    for (i = 0; i < 256; i++) {
        val = norm * 1000000. * exp(-(l_float32)((i - mean) * (i - mean)) /
                                      (l_float32)(2 * stdev * stdev));
        total += (l_int32)val;
        numaSetValue(na, i, val);
    }
    fprintf(stderr, "Total = %d\n", total);

    return na;
}


