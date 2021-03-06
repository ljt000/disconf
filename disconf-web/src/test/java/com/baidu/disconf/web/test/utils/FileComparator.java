package com.baidu.disconf.web.test.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import difflib.Chunk;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

/**
 * 
 * @author knightliao
 * 
 */
public class FileComparator {

    private final File original;

    private final File revised;

    public FileComparator(File original, File revised) {
        this.original = original;
        this.revised = revised;
    }

    public List<Chunk<String>> getChangesFromOriginal() throws IOException {
        return getChunksByType(Delta.TYPE.CHANGE);
    }

    public List<Chunk<String>> getInsertsFromOriginal() throws IOException {
        return getChunksByType(Delta.TYPE.INSERT);
    }

    public List<Chunk<String>> getDeletesFromOriginal() throws IOException {
        return getChunksByType(Delta.TYPE.DELETE);
    }

    private List<Chunk<String>> getChunksByType(Delta.TYPE type) throws IOException {
        final List<Chunk<String>> listOfChanges = new ArrayList<Chunk<String>>();
        final List<Delta<String>> deltas = getDeltas();
        for (Delta<String> delta : deltas) {
            if (delta.getType() == type) {
                listOfChanges.add(delta.getRevised());
            }
        }
        return listOfChanges;
    }

    private List<Delta<String>> getDeltas() throws IOException {

        final List<String> originalFileLines = fileToLines(original);
        final List<String> revisedFileLines = fileToLines(revised);

        final Patch<String> patch = DiffUtils.diff(originalFileLines, revisedFileLines);

        return patch.getDeltas();
    }

    private List<String> fileToLines(File file) throws IOException {
        final List<String> lines = new ArrayList<String>();
        final BufferedReader in = new BufferedReader(new FileReader(file));
        try {
			String line;
			while ((line = in.readLine()) != null) {
			    lines.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			in.close();
		}

        return lines;
    }

}
