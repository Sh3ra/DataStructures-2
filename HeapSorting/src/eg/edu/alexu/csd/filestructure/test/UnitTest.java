package eg.edu.alexu.csd.filestructure.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import javax.management.RuntimeErrorException;

import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.INode;
import eg.edu.alexu.csd.filestructure.sort.ISort;
import org.junit.Assert;
import org.junit.Test;

public class UnitTest {
    private final boolean debug = false;

    public UnitTest() {
    }

    @Test
    public void testRootNull() {
        IHeap<String> heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);
        INode root = null;

        try {
            root = heap.getRoot();
            Assert.assertNull("Root is not null", root);
        } catch (RuntimeErrorException var4) {
        } catch (Throwable var5) {
            TestRunner.fail("Fail to getRoot of heap", var5);
        }

    }

    @Test
    public void testGetRoot() {
        IHeap<String> heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);
        INode root = null;

        try {
            heap.insert("Soso");
            root = heap.getRoot();
            Assert.assertEquals("Soso", root.getValue());
        } catch (Throwable var4) {
            TestRunner.fail("Fail to getRoot of heap", var4);
        }

    }

    @Test
    public void testGetRootMulti() {
        IHeap<Integer> heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);
        INode root = null;

        try {
            Integer max = -2147483648;

            for(int i = 0; i < 10000; ++i) {
                Random r = new Random();
                int val = r.nextInt(2147483647);
                heap.insert(val);
                max = Math.max(max, val);
            }

            root = heap.getRoot();
            Assert.assertEquals(max, root.getValue());
        } catch (Throwable var7) {
            TestRunner.fail("Fail to getRoot of heap", var7);
        }

    }

    @Test
    public void testGetRootInsertingThenRemoving() {
        IHeap<Integer> heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);
        INode root = null;

        try {
            int i;
            for(i = 0; i < 10000; ++i) {
                heap.insert(3);
            }

            for(i = 0; i < 10000; ++i) {
                heap.extract();
            }

            root = heap.getRoot();
            Assert.assertNull("Root is not null", root);
        } catch (RuntimeErrorException var4) {
        } catch (Throwable var5) {
            TestRunner.fail("Fail to getRoot of heap", var5);
        }

    }

    @Test
    public void testHeapSize() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            Assert.assertEquals(0L, (long)heap.size());
        } catch (Throwable var3) {
            TestRunner.fail("Fail to get Heap size", var3);
        }

    }

    @Test
    public void testHeapSizeWithInsertionAndExtraction() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            Random r2 = new Random();
            int check = r2.nextInt(10000);

            int i;
            for(i = 0; i < 10000; ++i) {
                Random r = new Random();
                int val = r.nextInt(2147483647);
                heap.insert(val);
                --check;
                if (check == 0) {
                    Assert.assertEquals((long)(i + 1), (long)heap.size());
                }
            }

            check = r2.nextInt(1000);

            for(i = 0; i < 10000; ++i) {
                heap.extract();
                --check;
                if (check == 0) {
                    Assert.assertEquals((long)(10000 - i - 1), (long)heap.size());
                }
            }
        } catch (Throwable var7) {
            TestRunner.fail("Fail to get heap size", var7);
        }

    }

    @Test
    public void testGetChildrenAndParentPointers() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            heap.insert(1);
            heap.insert(2);
            heap.insert(4);
            heap.insert(3);
            heap.insert(0);
            heap.insert(5);
            INode<Integer> root = heap.getRoot();
            Assert.assertEquals(5L, (long)(Integer)root.getValue());
            Assert.assertEquals(3L, (long)(Integer)root.getLeftChild().getValue());
            Assert.assertEquals(4L, (long)(Integer)root.getRightChild().getValue());
            Assert.assertEquals(1L, (long)(Integer)root.getLeftChild().getLeftChild().getValue());
            Assert.assertEquals(0L, (long)(Integer)root.getLeftChild().getRightChild().getValue());
            Assert.assertEquals(2L, (long)(Integer)root.getRightChild().getLeftChild().getValue());
            Assert.assertEquals(5L, (long)(Integer)root.getLeftChild().getLeftChild().getParent().getParent().getValue());
        } catch (Throwable var3) {
            TestRunner.fail("Fail to get child/parent pointer", var3);
        }

    }

    @Test
    public void testGetNullChildrenAndParentPointers() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            heap.insert(1);
            Assert.assertNull(heap.getRoot().getLeftChild());
            Assert.assertNull(heap.getRoot().getRightChild());
            Assert.assertNull(heap.getRoot().getParent());
        } catch (Throwable var3) {
            TestRunner.fail("Fail to get child/parent pointer", var3);
        }

    }

    @Test
    public void testHeapifyWithNullParameter() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            heap.insert("soso");
            heap.heapify((INode)null);
            Assert.assertEquals(1L, (long)heap.size());
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to handle null in heapify method", var4);
        }

    }

    @Test
    public void testInsertWithNullParameter() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            heap.insert((Comparable)null);
            Assert.assertEquals(0L, (long)heap.size());
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to handle null in insert method", var4);
        }

    }

    @Test
    public void testInsertNormal() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            heap.insert("Soso");
            heap.insert("Toto");
            Assert.assertEquals(2L, (long)heap.size());
            Assert.assertEquals("Toto", heap.getRoot().getValue());
        } catch (Throwable var3) {
            TestRunner.fail("Fail to insert to heap", var3);
        }

    }

    @Test(
            timeout = 7000L
    )
    public void testInsertIsLgN() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            for(int i = 0; i < 10000000; ++i) {
                heap.insert("soso");
            }
        } catch (Throwable var3) {
            TestRunner.fail("Fail to insert to heap", var3);
        }

    }

    @Test
    public void testExtractNormal() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            Integer max = -2147483648;
            Integer secondMax = -2147483648;

            for(int i = 0; i < 10000; ++i) {
                Random r = new Random();
                int val = r.nextInt(2147483647);
                heap.insert(val);
                if (val > max) {
                    secondMax = max;
                    max = val;
                } else if (val >= secondMax) {
                    secondMax = val;
                }
            }

            Assert.assertEquals(max, heap.extract());
            Assert.assertEquals(secondMax, heap.extract());
        } catch (Throwable var7) {
            TestRunner.fail("Fail to extract element from heap", var7);
        }

    }

    @Test
    public void testExtractEmptyHeap() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            Integer i = (Integer)heap.extract();
            Assert.assertNull(i);
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to handle extracting from empty heap", var4);
        }

    }

    @Test(
            timeout = 7000L
    )
    public void testExtractLgN() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            int i;
            for(i = 0; i < 10000000; ++i) {
                heap.insert("soso");
            }

            for(i = 0; i < 10000000; ++i) {
                heap.extract();
            }
        } catch (Throwable var3) {
            TestRunner.fail("Fail to handle inserting or extracting from empty heap", var3);
        }

    }

    @Test
    public void testExtractAfterInsertingAndExtractingAllElements() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            int i;
            for(i = 0; i < 1000; ++i) {
                heap.insert("soso");
            }

            for(i = 0; i < 1000; ++i) {
                heap.extract();
            }

            String s = (String)heap.extract();
            Assert.assertNull(s);
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to handle extracting after inserting and removing all elements", var4);
        }

    }

    @Test
    public void testBuildHeapWithNull() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            heap.build((Collection)null);
            Assert.assertEquals(0L, (long)heap.size());
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to handle build with null paramter", var4);
        }

    }

    @Test
    public void testBuildHeapWithEmptyArray() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            heap.build(new ArrayList());
            Assert.assertEquals(0L, (long)heap.size());
        } catch (Throwable var3) {
            TestRunner.fail("Fail to handle build with empty array", var3);
        }

    }

    @Test(
            timeout = 1000L
    )
    public void testBuildIsN() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            ArrayList<Integer> arr = new ArrayList();

            for(int i = 0; i < 1000000; ++i) {
                arr.add(i);
            }

            heap.build(arr);
        } catch (Throwable var4) {
            TestRunner.fail("Fail to build heap", var4);
        }

    }

    @Test
    public void testNormalBuild() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            ArrayList<Integer> arr = new ArrayList();

            int i;
            for(i = 0; i < 1000000; ++i) {
                arr.add(i);
            }

            heap.build(arr);

            for(i = 999999; i >= 0; --i) {
                Assert.assertEquals((long)i, (long)(Integer)heap.extract());
            }
        } catch (Throwable var4) {
            TestRunner.fail("Fail to build heap", var4);
        }

    }

    @Test
    public void testRandomBuild() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            ArrayList<Integer> arr = new ArrayList();
            Random r = new Random();
            PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());

            int i;
            for(i = 0; i < 1000000; ++i) {
                int val = r.nextInt(2147483647);
                arr.add(val);
                pq.add(val);
            }

            heap.build(arr);

            for(i = 0; i < 1000000; ++i) {
                Assert.assertEquals((long)(Integer)pq.poll(), (long)(Integer)heap.extract());
            }
        } catch (Throwable var7) {
            TestRunner.fail("Fail to build heap", var7);
        }

    }

    @Test
    public void testHeapSortWithNullParameter() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            IHeap<Integer> heap = sort.heapSort((ArrayList)null);
            Assert.assertEquals(0L, (long)heap.size());
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail in heap sort", var4);
        }

    }

    @Test
    public void testHeapSortWithEmptyArray() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            IHeap<Integer> heap = sort.heapSort(new ArrayList());
            Assert.assertEquals(0L, (long)heap.size());
        } catch (Throwable var3) {
            TestRunner.fail("Fail in heap sort", var3);
        }

    }

    @Test
    public void testNormalHeapSortSmallInput() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            ArrayList<Integer> arr = new ArrayList();
            Random r = new Random();

            for(int i = 0; i < 10; ++i) {
                int val = r.nextInt(2147483647);
                arr.add(val);
            }

            IHeap<Integer> heap = sort.heapSort(arr);
            Collections.sort(arr);
            ArrayList<Integer> heapRes = new ArrayList();
            Queue<INode<Integer>> q = new LinkedList();
            q.add(heap.getRoot());

            while(!q.isEmpty()) {
                INode<Integer> curNode = (INode)q.poll();
                if (curNode != null) {
                    heapRes.add((Integer)curNode.getValue());
                    q.add(curNode.getLeftChild());
                    q.add(curNode.getRightChild());
                }
            }

            Assert.assertEquals((long)arr.size(), (long)heapRes.size());

            for(int i = 0; i < arr.size(); ++i) {
                Assert.assertEquals(arr.get(i), heapRes.get(i));
            }
        } catch (Throwable var8) {
            TestRunner.fail("Fail in heap sort", var8);
        }

    }

    @Test(
            timeout = 5000L
    )
    public void testNormalHeapSortBigInput() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            ArrayList<Integer> arr = new ArrayList();
            Random r = new Random();

            for(int i = 0; i < 1000000; ++i) {
                int val = r.nextInt(2147483647);
                arr.add(val);
            }

            IHeap<Integer> heap = sort.heapSort(arr);
            Collections.sort(arr);
            ArrayList<Integer> heapRes = new ArrayList();
            Queue<INode<Integer>> q = new LinkedList();
            q.add(heap.getRoot());

            while(!q.isEmpty()) {
                INode<Integer> curNode = (INode)q.poll();
                if (curNode != null) {
                    heapRes.add((Integer)curNode.getValue());
                    q.add(curNode.getLeftChild());
                    q.add(curNode.getRightChild());
                }
            }

            Assert.assertEquals((long)arr.size(), (long)heapRes.size());

            for(int i = 0; i < arr.size(); ++i) {
                Assert.assertEquals(arr.get(i), heapRes.get(i));
            }
        } catch (Throwable var8) {
            TestRunner.fail("Fail in heap sort", var8);
        }

    }

    @Test
    public void testSlowSortWithNullParameter() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            sort.sortSlow((ArrayList)null);
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to slow sort", var4);
        }

    }

    @Test
    public void testSlowSortWithEmptyArray() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            sort.sortSlow(new ArrayList());
        } catch (Throwable var3) {
            TestRunner.fail("Fail to slow sort", var3);
        }

    }

    @Test
    public void testSlowSortWithSmallInput() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            ArrayList<Integer> arr = new ArrayList();
            Random r = new Random();

            int i;
            for( i = 0; i < 10; ++i) {
                i = r.nextInt(2147483647);
                arr.add(i);
            }

            ArrayList<Integer> arr2 = new ArrayList(arr);
            sort.sortSlow(arr2);
            Collections.sort(arr);
            Assert.assertEquals((long)arr.size(), (long)arr2.size());

            for(i = 0; i < arr.size(); ++i) {
                Assert.assertEquals(arr.get(i), arr2.get(i));
            }
        } catch (Throwable var6) {
            TestRunner.fail("Fail in slow sort", var6);
        }

    }

    @Test(
            timeout = 10000L
    )
    public void testSlowSortWithBigInput() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            ArrayList<Integer> arr = new ArrayList();
            Random r = new Random();

            int i;
            for( i = 0; i < 10000; ++i) {
                i = r.nextInt(2147483647);
                arr.add(i);
            }

            ArrayList<Integer> arr2 = new ArrayList(arr);
            sort.sortSlow(arr2);
            Collections.sort(arr);
            Assert.assertEquals((long)arr.size(), (long)arr2.size());

            for(i = 0; i < arr.size(); ++i) {
                Assert.assertEquals(arr.get(i), arr2.get(i));
            }
        } catch (Throwable var6) {
            TestRunner.fail("Fail in slow sort", var6);
        }

    }

    @Test
    public void testFastSortWithNullParameter() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            sort.sortFast((ArrayList)null);
        } catch (RuntimeErrorException var3) {
        } catch (Throwable var4) {
            TestRunner.fail("Fail to fast sort", var4);
        }

    }

    @Test
    public void testFastSortWithEmptyArray() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            sort.sortFast(new ArrayList());
        } catch (Throwable var3) {
            TestRunner.fail("Fail to slow sort", var3);
        }

    }

    @Test
    public void testFastSortWithSmallInput() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            ArrayList<Integer> arr = new ArrayList();
            Random r = new Random();

            int i;
            for( i = 0; i < 10; ++i) {
                i = r.nextInt(2147483647);
                arr.add(i);
            }

            ArrayList<Integer> arr2 = new ArrayList(arr);
            sort.sortFast(arr2);
            Collections.sort(arr);
            Assert.assertEquals((long)arr.size(), (long)arr2.size());

            for(i = 0; i < arr.size(); ++i) {
                Assert.assertEquals(arr.get(i), arr2.get(i));
            }
        } catch (Throwable var6) {
            TestRunner.fail("Fail in fast sort", var6);
        }

    }

    @Test(
            timeout = 5000L
    )
    public void testFastSortWithBigInput() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            ArrayList<Integer> arr = new ArrayList();
            Random r = new Random();

            int i;
            for( i = 0; i < 1000000; ++i) {
                i = r.nextInt(2147483647);
                arr.add(i);
            }

            ArrayList<Integer> arr2 = new ArrayList(arr);
            sort.sortFast(arr2);
            Collections.sort(arr);
            Assert.assertEquals((long)arr.size(), (long)arr2.size());

            for(i = 0; i < arr.size(); ++i) {
                Assert.assertEquals(arr.get(i), arr2.get(i));
            }
        } catch (Throwable var6) {
            TestRunner.fail("Fail in fast sort", var6);
        }

    }

    @Test(
            timeout = 5000L
    )
    public void testFastSortWithReverseInput() {
        ISort sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);

        try {
            ArrayList<Integer> arr = new ArrayList();

            for(int i = 0; i < 1000000; ++i) {
                arr.add(1000000 - i);
            }

            ArrayList<Integer> arr2 = new ArrayList(arr);
            sort.sortFast(arr2);
            Collections.sort(arr);
            Assert.assertEquals((long)arr.size(), (long)arr2.size());

            for(int i = 0; i < arr.size(); ++i) {
                Assert.assertEquals(arr.get(i), arr2.get(i));
            }
        } catch (Throwable var5) {
            TestRunner.fail("Fail in fast sort", var5);
        }

    }

    @Test
    public void testStressHeap() {
        IHeap heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);

        try {
            PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
            Random r = new Random();
            Random pick = new Random();

            for(int i = 0; i < 1000000; ++i) {
                int numToPick = pick.nextInt(2147483647);
                int val = r.nextInt(2147483647);
                if (numToPick % 4 == 0) {
                    if (!pq.isEmpty()) {
                        Assert.assertEquals(pq.poll(), heap.extract());
                    } else {
                        Assert.assertEquals(0L, (long)heap.size());
                    }
                } else {
                    pq.add(val);
                    heap.insert(val);
                }
            }
        } catch (Throwable var8) {
            TestRunner.fail("Fail in heap", var8);
        }

    }

    @Test
    public void testStressHeapWithCustomComparable() {
        IHeap<UnitTest.Pair> heap = (IHeap)TestRunner.getImplementationInstanceForInterface(IHeap.class);
        String[] randomWords = new String[]{"bells", "remain", "crabby", "comfortable", "stamp", "quickest", "sulky", "worm", "vigorous", "grandfather", "crook", "show", "second", "water", "ask", "finger", "scent", "encourage", "harsh", "kaput", "spotted", "room", "harmony", "bear", "desk", "dramatic", "leg", "elite", "drop", "overjoyed", "suspend", "selection", "tow", "pancake", "doubt", "laugh", "coast", "slow", "narrow", "language", "hand", "preach", "shaky", "flavor", "spark", "uptight", "pail", "jog", "unadvised", "fortunate", "exultant", "clumsy", "rot", "train", "curtain", "spurious", "middle", "dare", "wheel", "snake", "jail", "crooked", "smoggy", "elfin", "abnormal", "skip", "skate", "basket", "amount", "invention", "vegetable", "unequaled", "part", "erratic", "branch", "car", "glib", "fish", "order", "deranged", "bomb", "overrated", "orange", "enjoy", "judicious", "finger", "cheap", "meek", "gruesome", "defective", "wicked", "bashful", "rotten", "ground", "delicious", "cellar", "chalk", "dress", "north", "serious"};

        try {
            PriorityQueue<UnitTest.Pair> pq = new PriorityQueue(Collections.reverseOrder());
            Random r = new Random();
            Random pick = new Random();

            for(int i = 0; i < 1000000; ++i) {
                int numToPick = pick.nextInt(2147483647);
                UnitTest.Pair p = new UnitTest.Pair(r.nextInt(2147483647), randomWords[r.nextInt(randomWords.length)]);
                if (numToPick % 4 == 0) {
                    if (!pq.isEmpty()) {
                        UnitTest.Pair p1 = (UnitTest.Pair)pq.poll();
                        UnitTest.Pair p2 = (UnitTest.Pair)heap.extract();
                        Assert.assertEquals((long)p1.key, (long)p2.key);
                        Assert.assertEquals(p1.val, p2.val);
                    } else {
                        Assert.assertEquals(0L, (long)heap.size());
                    }
                } else {
                    pq.add(p);
                    heap.insert(p);
                }
            }
        } catch (Throwable var11) {
            TestRunner.fail("Fail in heap", var11);
        }

    }

    @Test
    public void testStressSlowSortWithCustomComparable() {
        ISort<UnitTest.Pair> sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);
        String[] randomWords = new String[]{"bells", "remain", "crabby", "comfortable", "stamp", "quickest", "sulky", "worm", "vigorous", "grandfather", "crook", "show", "second", "water", "ask", "finger", "scent", "encourage", "harsh", "kaput", "spotted", "room", "harmony", "bear", "desk", "dramatic", "leg", "elite", "drop", "overjoyed", "suspend", "selection", "tow", "pancake", "doubt", "laugh", "coast", "slow", "narrow", "language", "hand", "preach", "shaky", "flavor", "spark", "uptight", "pail", "jog", "unadvised", "fortunate", "exultant", "clumsy", "rot", "train", "curtain", "spurious", "middle", "dare", "wheel", "snake", "jail", "crooked", "smoggy", "elfin", "abnormal", "skip", "skate", "basket", "amount", "invention", "vegetable", "unequaled", "part", "erratic", "branch", "car", "glib", "fish", "order", "deranged", "bomb", "overrated", "orange", "enjoy", "judicious", "finger", "cheap", "meek", "gruesome", "defective", "wicked", "bashful", "rotten", "ground", "delicious", "cellar", "chalk", "dress", "north", "serious"};

        try {
            Random r = new Random();
            ArrayList<UnitTest.Pair> arr = new ArrayList();

            for(int i = 0; i < 10000; ++i) {
                UnitTest.Pair p = new UnitTest.Pair(r.nextInt(2147483647), randomWords[r.nextInt(randomWords.length)]);
                arr.add(p);
            }

            ArrayList<UnitTest.Pair> copy = new ArrayList(arr);
            Collections.sort(arr);
            sort.sortSlow(copy);

            for(int i = 0; i < arr.size(); ++i) {
                Assert.assertEquals((long)((UnitTest.Pair)arr.get(i)).key, (long)((UnitTest.Pair)copy.get(i)).key);
                Assert.assertEquals(((UnitTest.Pair)arr.get(i)).val, ((UnitTest.Pair)copy.get(i)).val);
            }
        } catch (Throwable var7) {
            TestRunner.fail("Fail in slow sort", var7);
        }

    }

    @Test
    public void testStressFastSortWithCustomComparable() {
        ISort<UnitTest.Pair> sort = (ISort)TestRunner.getImplementationInstanceForInterface(ISort.class);
        String[] randomWords = new String[]{"bells", "remain", "crabby", "comfortable", "stamp", "quickest", "sulky", "worm", "vigorous", "grandfather", "crook", "show", "second", "water", "ask", "finger", "scent", "encourage", "harsh", "kaput", "spotted", "room", "harmony", "bear", "desk", "dramatic", "leg", "elite", "drop", "overjoyed", "suspend", "selection", "tow", "pancake", "doubt", "laugh", "coast", "slow", "narrow", "language", "hand", "preach", "shaky", "flavor", "spark", "uptight", "pail", "jog", "unadvised", "fortunate", "exultant", "clumsy", "rot", "train", "curtain", "spurious", "middle", "dare", "wheel", "snake", "jail", "crooked", "smoggy", "elfin", "abnormal", "skip", "skate", "basket", "amount", "invention", "vegetable", "unequaled", "part", "erratic", "branch", "car", "glib", "fish", "order", "deranged", "bomb", "overrated", "orange", "enjoy", "judicious", "finger", "cheap", "meek", "gruesome", "defective", "wicked", "bashful", "rotten", "ground", "delicious", "cellar", "chalk", "dress", "north", "serious"};

        try {
            Random r = new Random();
            ArrayList<UnitTest.Pair> arr = new ArrayList();

            for(int i = 0; i < 10000; ++i) {
                UnitTest.Pair p = new UnitTest.Pair(r.nextInt(2147483647), randomWords[r.nextInt(randomWords.length)]);
                arr.add(p);
            }

            ArrayList<UnitTest.Pair> copy = new ArrayList(arr);
            Collections.sort(arr);
            sort.sortFast(copy);

            for(int i = 0; i < arr.size(); ++i) {
                Assert.assertEquals((long)((UnitTest.Pair)arr.get(i)).key, (long)((UnitTest.Pair)copy.get(i)).key);
                Assert.assertEquals(((UnitTest.Pair)arr.get(i)).val, ((UnitTest.Pair)copy.get(i)).val);
            }
        } catch (Throwable var7) {
            TestRunner.fail("Fail in fast sort", var7);
        }

    }

    private class Pair implements Comparable<UnitTest.Pair> {
        private int key;
        private String val;

        public Pair(int key, String val) {
            this.key = key;
            this.val = val;
        }

        public int compareTo(UnitTest.Pair o) {
            String s1 = this.val + Objects.hash(new Object[]{this.key, this.val});
            String s2 = o.val + Objects.hash(new Object[]{o.key, o.val});
            return s1.compareTo(s2);
        }
    }
}
