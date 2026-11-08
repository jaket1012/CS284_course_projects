package pa2;

/**
 * PUT YOUR INFORMATION BELOW PRIOR TO SUBMISSION
 * Name:Jake Talvy
 * Pledge:I pledge my honor that I have abided by the Stevens Honor System.
 */

import java.util.*;

public class TriangularList<E> {
    public List<List<E>> metaList;
    public int size;

    public TriangularList() {
        size = 0;
        metaList = new ArrayList<>();
    }

    public double location(int idx){
        return ((-1.0 + Math.sqrt(1.0 - (4.0 * 1.0 * -2.0 * idx)))/2);
    }

    public E get(int idx) {
        if (idx < 0 || idx >= size){
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        if (idx == 0){
            return metaList.get(0).get(0);
        }
        double location = location(idx);
        double listLocation = location % 1;
        if (listLocation == 0.0){
            return metaList.get((int) location).get(0);
        }
        int metaListLocation = (int) (location - listLocation);
        return metaList.get(metaListLocation).get(((int) (listLocation * metaListLocation)) + 1);
    }

    public void set(int idx, E value) {
        if (idx < 0 || idx> size){
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        if (idx == 0){
            metaList.get(0).set(0, value);
        }
        double location = location(idx);
        double listLocation = location % 1;
        if (listLocation == 0.0){
            metaList.get((int) location).set(0, value);
        }
        else {
            int metaListLocation = (int) (location - listLocation);
            metaList.get(metaListLocation).set(((int) listLocation * (metaListLocation)) + 1, value);
        }
    }

    public void insert(int idx, E value) {
        if (idx < 0 || idx > size){
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        double location = location(idx);
        double listLocation = location % 1;
        int metaListLocation = (int) (location - listLocation);
        if (idx == size){
            if (listLocation == 0){
                ArrayList<E> newList = new ArrayList<E>(metaListLocation + 1);
                newList.add(0, value);
                metaList.add((int) location, newList);
            }else {
                metaList.get(metaListLocation).add((int) (listLocation * metaListLocation + 1), value);
            }
            size++;
            return;
        }
        if (idx == 0){
            metaList.get(0).add(0, value);
        }
        else if (listLocation == 0.0){
            metaList.get((int) location).add(0, value);
        }
        else {
            metaList.get(metaListLocation).add((int) (listLocation * metaListLocation) + 1, value);
        }
        double lastLocation = location(size);
        double lastListLocation = lastLocation % 1;
        int lastMetaListLocation = (int) (lastLocation - lastListLocation);
        for (int i = 0; i < (int) lastMetaListLocation + 1; i++){
            if (metaList.get(i).toArray().length > i + 1){
                E temp = metaList.get(i).getLast();
                if (i == (lastMetaListLocation)) {
                    ArrayList<E> newList = new ArrayList<E>(lastMetaListLocation + 1);
                    newList.add(0, temp);
                    metaList.add((int) location, newList);
                }else {
                    metaList.get(i+1).addFirst(temp);
                }
                metaList.get(i).removeLast();
            }
        }
        size++;
    }

    public void remove(int idx) {
        if (idx < 0 || idx >= size){
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        double location = location(idx);
        double listLocation = location % 1;
        int metaListLocation = (int) (location - listLocation);
        if (idx == size){
            metaList.get(metaListLocation).removeLast();
            if (metaList.get(metaListLocation).toArray().length == 0){
                metaList.remove(metaListLocation);
            }
        }
        if (idx == 0){
            metaList.get(0).removeFirst();
        }
        else if (listLocation == 0.0){
            metaList.get(metaListLocation).removeFirst();
        }
        else {
            metaList.get(metaListLocation).remove((int) (listLocation * metaListLocation) + 1);
        }
        double lastLocation = location(size);
        double lastListLocation = lastLocation % 1;
        int lastMetaListLocation = (int) (lastLocation - lastListLocation);
        if (lastListLocation == 0.0){
            lastMetaListLocation--;
        }
        for (int i = metaListLocation; i < (int) lastMetaListLocation + 1; i++){
            if (metaList.get(i).toArray().length < i + 1){
                if (i == (lastMetaListLocation)) {
                    if (metaList.getLast().toArray().length == 0){
                        metaList.removeLast();
                    }
                    break;
                }
                E temp = metaList.get(i+1).getFirst();
                metaList.get(i).add(metaList.get(i).toArray().length, temp);
                metaList.get(i+1).removeFirst();
            }
        }
        size--;
    }

    public String toString() {
        if (size == 0){
            return "[]";
        }
        StringBuilder string = new StringBuilder("[");
        boolean firstItem = true;

        for (List<E> innerList: metaList){
            if (!firstItem){
                string.append(", ");
            }
            string.append(innerList);
            firstItem = false;
        }
        string.append("]");
        return string.toString();
    }

    public int size() {
        return size;
    }
}
