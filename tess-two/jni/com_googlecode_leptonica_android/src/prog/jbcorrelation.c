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
 * jbcorrelation.c
 *
 *     jbcorrelation dirin thresh weight rootname [firstpage npages]
 *
 *         dirin:  directory of input pages
 *         thresh: 0.80 - 0.85 is a reasonable compromise between accuracy
 *                 and number of classes, for characters
 *         weight: 0.6 seems to work reasonably with thresh = 0.8.
 *         rootname: used for naming the two output files (templates
 *                   and c.c. data)
 *
 *     Note: all components larger than a default size are not saved.
 *           The default size is given in jbclass.c.
 */

#include "allheaders.h"

    /* Choose one of these */
#define  COMPONENTS  JB_CONN_COMPS
/* #define  COMPONENTS  JB_CHARACTERS */
/* #define  COMPONENTS  JB_WORDS */

#define   BUF_SIZE         512

    /* select additional debug output */
#define   DEBUG_TEST_DATA_IO        0
#define   RENDER_DEBUG              1
#define   DISPLAY_DIFFERENCE        0
#define   DISPLAY_ALL_INSTANCES     0

    /* for display output of all instances, sorted by class */
#define   X_SPACING                10
#define   Y_SPACING                15
#define   MAX_OUTPUT_WIDTH        400


main(int    argc,
     char **argv)
{
char         filename[BUF_SIZE];
char        *dirin, *rootname, *fname;
l_int32      i, firstpage, npages, nfiles;
l_float32    thresh, weight;
JBDATA      *data;
JBCLASSER   *classer;
SARRAY      *safiles;
PIX         *pix, *pixt;
PIXA        *pixa, *pixadb;
static char  mainName[] = "jbcorrelation";

    if (argc != 5 && argc != 7)
	return ERROR_INT(" Syntax: jbcorrelation dirin thresh weight "
                         "rootname [firstpage, npages]", mainName, 1);

    dirin = argv[1];
    thresh = atof(argv[2]);
    weight = atof(argv[3]);
    rootname = argv[4];

    if (argc == 5) {
        firstpage = 0;
	npages = 0;
    }
    else {
        firstpage = atoi(argv[5]);
        npages = atoi(argv[6]);
    }

#if 0

    /*--------------------------------------------------------------*/

    jbCorrelation(dirin, thresh, weight, COMPONENTS, rootname,
                  firstpage, npages, 1);

    /*--------------------------------------------------------------*/

#else

    /*--------------------------------------------------------------*/

    safiles = getSortedPathnamesInDirectory(dirin, NULL, firstpage, npages);
    nfiles = sarrayGetCount(safiles);

    sarrayWriteStream(stderr, safiles);

        /* Classify components on requested pages */
    startTimer();
    classer = jbCorrelationInit(COMPONENTS, 0, 0, thresh, weight);
    jbAddPages(classer, safiles);
    fprintf(stderr, "Time to generate classes: %6.3f sec\n", stopTimer());

        /* Save and write out the result */
    data = jbDataSave(classer);
    jbDataWrite(rootname, data);
    fprintf(stderr, "Number of classes: %d\n", classer->nclass);

        /* Render the pages from the classifier data.
	 * Use debugflag == FALSE to omit outlines of each component. */
    pixa = jbDataRender(data, FALSE);

        /* Write the pages out */
    npages = pixaGetCount(pixa);
    if (npages != nfiles)
        fprintf(stderr, "npages = %d, nfiles = %d, not equal!\n",
	        npages, nfiles);
    for (i = 0; i < npages; i++) {
        pix = pixaGetPix(pixa, i, L_CLONE);
	snprintf(filename, BUF_SIZE, "%s.%05d", rootname, i);
	fprintf(stderr, "filename: %s\n", filename);
	pixWrite(filename, pix, IFF_PNG);
	pixDestroy(&pix);
    }

#if  DISPLAY_DIFFERENCE
    fname = sarrayGetString(safiles, 0, 0);
    pixt = pixRead(fname);
    pix = pixaGetPix(pixa, 0, L_CLONE);
    pixXor(pixt, pixt, pix);
    pixWrite("junk_output_diff", pixt, IFF_PNG);
    pixDestroy(&pix);
    pixDestroy(&pixt);
#endif  /* DISPLAY_DIFFERENCE */

#if  DEBUG_TEST_DATA_IO
{ JBDATA  *newdata;
  PIX     *newpix;
  PIXA    *newpixa;
  l_int32  same, iofail;
        /* Read the data back in and render the pages */
    newdata = jbDataRead(rootname);
    newpixa = jbDataRender(newdata, FALSE);
    iofail = FALSE;
    for (i = 0; i < npages; i++) {
        pix = pixaGetPix(pixa, i, L_CLONE);
        newpix = pixaGetPix(newpixa, i, L_CLONE);
	pixEqual(pix, newpix, &same);
	if (!same) {
	    iofail = TRUE;
	    fprintf(stderr, "pix on page %d are unequal!\n", i);
	}
	pixDestroy(&pix);
	pixDestroy(&newpix);

    }
    if (iofail)
	fprintf(stderr, "read/write for jbdata fails\n");
    else
	fprintf(stderr, "read/write for jbdata succeeds\n");
    jbDataDestroy(&newdata);
    pixaDestroy(&newpixa);
}
#endif  /* DEBUG_TEST_DATA_IO */

#if  RENDER_DEBUG
	/* Use debugflag == TRUE to see outlines of each component. */
    pixadb = jbDataRender(data, TRUE);
        /* Write the debug pages out */
    npages = pixaGetCount(pixadb);
    for (i = 0; i < npages; i++) {
        pix = pixaGetPix(pixadb, i, L_CLONE);
	snprintf(filename, BUF_SIZE, "%s.db.%05d", rootname, i);
	fprintf(stderr, "filename: %s\n", filename);
	pixWrite(filename, pix, IFF_PNG);
	pixDestroy(&pix);
    }
    pixaDestroy(&pixadb);
#endif  /* RENDER_DEBUG */

#if  DISPLAY_ALL_INSTANCES
	/* display all instances, organized by template */
    pix = pixaaDisplayByPixa(classer->pixaa,
                             X_SPACING, Y_SPACING, MAX_OUTPUT_WIDTH);
    pixWrite("output_instances", pix, IFF_PNG);
    pixDestroy(&pix);
#endif  /* DISPLAY_ALL_INSTANCES */

    pixaDestroy(&pixa);
    sarrayDestroy(&safiles);
    jbClasserDestroy(&classer);
    jbDataDestroy(&data);

    /*--------------------------------------------------------------*/

#endif

    return 0;
}

