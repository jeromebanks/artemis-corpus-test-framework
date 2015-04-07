package com.spinn3r.artemis.corpus.test;

import difflib.DiffUtils;
import difflib.Patch;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class DiffGenerator {

    private static final int UNIFIED_DIFF_CONTEXT_SIZE = 5;

    public static String diff( String original, String revised ) {

        List<String> originalLines = Arrays.asList( original.split( "\n" ) );
        List<String> revisedLines  = Arrays.asList( revised.split( "\n" ) );

        // Compute diff. Get the Patch object. Patch is the container for computed deltas.
        Patch patch = DiffUtils.diff( originalLines, revisedLines );

        List<String> unifiedDiffs = DiffUtils.generateUnifiedDiff( original, revised, originalLines, patch, UNIFIED_DIFF_CONTEXT_SIZE );

        StringBuilder buff = new StringBuilder();

        for (String unifiedDiff : unifiedDiffs) {
            buff.append( unifiedDiff );
            buff.append( "\n" );
        }

        return buff.toString();

    }

}
