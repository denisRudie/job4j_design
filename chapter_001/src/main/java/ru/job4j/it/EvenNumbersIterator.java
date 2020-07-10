package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] nums;
    private int counter = 0;

    public EvenNumbersIterator(final int[] numbers) {
       this.nums = numbers;
    }

//    @Override
//    public boolean hasNext() {
//        if (nums[counter] % 2 == 0) {
//            return true;
//        } else {
//            while (counter < nums.length - 1) {
//                counter++;
//                if (nums[counter] % 2 == 0) {
//                    return true;
//                }
//            } return false;
//        }
//    }

    @Override
    public boolean hasNext() {
        while (counter < (nums.length - 1) && nums[counter] % 2 != 0) {
            counter++;
        }
        return (nums[counter] % 2 == 0);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return nums[counter++];
    }
}
