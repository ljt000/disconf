package com.baidu.disconf.web.common.comparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import difflib.Chunk;
import difflib.Delta;

/**
 * @author knightliao
 */
public abstract class CommonComparator {

    public List<Chunk<String>> getChangesFromOriginal() throws IOException {
        return getChunksByType(Delta.TYPE.CHANGE);
    }

    public List<Chunk<String>> getInsertsFromOriginal() throws IOException {
        return getChunksByType(Delta.TYPE.INSERT);
    }

    public List<Chunk<String>> getDeletesFromOriginal() throws IOException {
        return getChunksByType(Delta.TYPE.DELETE);
    }

    protected abstract List<Delta<String>> getDeltas() throws IOException;

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
}
