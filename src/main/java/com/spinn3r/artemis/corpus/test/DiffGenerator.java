package com.spinn3r.artemis.corpus.test;

import difflib.DiffUtils;
import difflib.Patch;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class DiffGenerator {

    private static final int UNIFIED_DIFF_CONTEXT_SIZE = 2;

    public static String diff( String original, String revised ) {

        List<String> originalLines = Arrays.asList( original.split( "\n" ) );
        List<String> revisedLines  = Arrays.asList( revised.split( "\n" ) );

        // Compute diff. Get the Patch object. Patch is the container for computed deltas.
        Patch patch = DiffUtils.diff( originalLines, revisedLines );

        // we don't need the full original txt...
        String originalText = "";
        String revisedText = "";

        List<String> unifiedDiffs = DiffUtils.generateUnifiedDiff( originalText, revisedText, originalLines, patch, UNIFIED_DIFF_CONTEXT_SIZE );

        // the unified diff returns the full text of each file as originalText and revisedText which we DO NOT need.
        unifiedDiffs.remove( 0 );
        unifiedDiffs.remove( 0 );

        StringBuilder buff = new StringBuilder();

        for (String unifiedDiff : unifiedDiffs) {
            buff.append( unifiedDiff );
            buff.append( "\n" );
        }

        return buff.toString();

    }

}
