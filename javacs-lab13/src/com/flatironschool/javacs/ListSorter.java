/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        if(list.size() == 0 || list.size() == 1){
        	return list;
        }
       List<T> temp = new LinkedList<T>(list.subList(0, list.size()/2));
       List<T> temp1 = new LinkedList<T>(list.subList(list.size()/2, list.size()));
       List<T> left = mergeSort(temp, comparator);
       List<T> right = mergeSort(temp1, comparator);
       return merge(left, right, comparator);
	}

	private List<T> merge(List<T> left, List<T> right, Comparator<T> comparator){
		List<T> finalList = new LinkedList<T>();
		while(left.size() > 0 && right.size() > 0){
			T lfirst = left.get(0);
			T rfirst = right.get(0);
			if(comparator.compare(lfirst, rfirst) < 0){
				finalList.add(lfirst);
				left.remove(0);
			}
			else{
				finalList.add(rfirst);
				right.remove(0);
			}
		}
		if(right.size() > 0){
			finalList.addAll(right);
		}
		else{
			finalList.addAll(left);
		}
		return finalList;
	}
	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        PriorityQueue<T> pq = new PriorityQueue<T>();
        pq.addAll(list);
        //for(int i = 0; i < list.size(); i ++){
        //	pq.offer(list.get(i));
        //}
        int i = 0;
        while(pq.size() > 0){
        	list.set(i, pq.poll());
        	i++;
        }
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
		heapSort(list,  comparator);
     	return list.subList(list.size() - k, list.size());
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
