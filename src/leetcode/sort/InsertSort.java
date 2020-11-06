package leetcode.sort;

public class InsertSort {
    public static void main(String[] args) {
        int[] temp = {4, 10, 3, 5, 1};
        insertSort(temp);
        for (int element : temp) {
            System.out.println(element);
        }
    }

    private static void insertSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] < nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode helper = new ListNode(0);
        ListNode prevNode = helper;
        ListNode currentNode = head;
        ListNode nextNode = null;

        while (currentNode != null) {
            nextNode = currentNode.next;

            while (prevNode.next != null && prevNode.next.val < currentNode.val) {
                prevNode = prevNode.next;
            }
            //insert
            currentNode.next = prevNode.next;
            prevNode.next = currentNode;

            prevNode = helper;
            currentNode = nextNode;
        }

        return helper.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
