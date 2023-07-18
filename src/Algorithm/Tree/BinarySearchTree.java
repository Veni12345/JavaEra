//package Algorithm.Tree;
//
///**
// * @author: Veni
// * @date: 2022/09/20 九月 星期二 15:54
// * @description: 二叉搜索树 实现
// */
//public class BinarySearchTree {
//
//    // Java实现二叉平衡搜索树的磁盘实现
//    // 定义节点类
//    class Node {
//        int key;
//        int leftChild;
//        int rightChild;
//        int height;
//        // 构造函数
//        public Node(int key, int leftChild, int rightChild, int height) {
//            this.key = key;
//            this.leftChild = leftChild;
//            this.rightChild = rightChild;
//            this.height = height;
//        }
//    }
//    // 定义磁盘存储类
//    class DiskStorage {
//        // 存储节点信息
//        public void saveNode(Node node) {
//            // 将节点信息写入磁盘
//        }
//        // 读取节点信息
//        public Node readNode(int offset) {
//            // 从磁盘读取节点信息
//            return node;
//        }
//        // 存储树的关系信息
//        public void saveRelation(int parentOffset, int childOffset, boolean isLeftChild) {
//            // 将关系信息写入磁盘
//        }
//        // 读取树的关系信息
//        public int[] readRelation(int parentOffset, boolean isLeftChild) {
//            // 从磁盘读取关系信息
//            return relation;
//        }
//    }
//    // 定义二叉平衡搜索树类
//    class AVLTree {
//        private DiskStorage diskStorage;
//        private int rootOffset;
//        // 构造函数
//        public AVLTree(DiskStorage diskStorage) {
//            this.diskStorage = diskStorage;
//            this.rootOffset = -1;
//        }
//        // 查找节点
//        public Node search(int key) {
//            int offset = rootOffset;
//            while (offset != -1) {
//                Node node = diskStorage.readNode(offset);
//                if (node.key == key) {
//                    return node;
//                } else if (node.key > key) {
//                    offset = node.leftChild;
//                } else {
//                    offset = node.rightChild;
//                }
//            }
//            return null;
//        }
//        // 插入节点
//        public void insert(int key) {
//            if (rootOffset == -1) {
//                Node node = new Node(key, -1, -1, 1);
//                diskStorage.saveNode(node);
//                rootOffset = 0;
//                return;
//            }
//            int offset = rootOffset;
//            while (true) {
//                Node node = diskStorage.readNode(offset);
//                if (node.key == key) {
//                    return;
//                } else if (node.key > key) {
//                    if (node.leftChild == -1) {
//                        Node newNode = new Node(key, -1, -1, 1);
//                        diskStorage.saveNode(newNode);
//                        node.leftChild = offset + 1;
//                        diskStorage.saveNode(node);
//                        diskStorage.saveRelation(offset, offset + 1, true);
//                        rebalance(offset);
//                        return;
//                    } else {
//                        offset = node.leftChild;
//                    }
//                } else {
//                    if (node.rightChild == -1) {
//                        Node newNode = new Node(key, -1, -1, 1);
//                        diskStorage.saveNode(newNode);
//                        node.rightChild = offset + 1;
//                        diskStorage.saveNode(node);
//                        diskStorage.saveRelation(offset, offset + 1, false);
//                        rebalance(offset);
//                        return;
//                    } else {
//                        offset = node.rightChild;
//                    }
//                }
//            }
//        }
//        // 删除节点
//        public void delete(int key) {
//            int offset = rootOffset;
//            int parentOffset = -1;
//            boolean isLeftChild = false;
//            while (offset != -1) {
//                Node node = diskStorage.readNode(offset);
//                if (node.key == key) {
//                    if (node.leftChild == -1 && node.rightChild == -1) {
//                        if (parentOffset == -1) {
//                            rootOffset = -1;
//                        } else {
//                            if (isLeftChild) {
//                                diskStorage.saveRelation(parentOffset, -1, true);
//                            } else {
//                                diskStorage.saveRelation(parentOffset, -1, false);
//                            }
//                        }
//                    } else if (node.leftChild == -1) {
//                        if (parentOffset == -1) {
//                            rootOffset = node.rightChild;
//                        } else {
//                            if (isLeftChild) {
//                                diskStorage.saveRelation(parentOffset, node.rightChild, true);
//                            } else {
//                                diskStorage.saveRelation(parentOffset, node.rightChild, false);
//                            }
//                        }
//                    } else if (node.rightChild == -1) {
//                        if (parentOffset == -1) {
//                            rootOffset = node.leftChild;
//                        } else {
//                            if (isLeftChild) {
//                                diskStorage.saveRelation(parentOffset, node.leftChild, true);
//                            } else {
//                                diskStorage.saveRelation(parentOffset, node.leftChild, false);
//                            }
//                        }
//                    } else {
//                        int successorOffset = findSuccessor(node.rightChild);
//                        Node successor = diskStorage.readNode(successorOffset);
//                        node.key = successor.key;
//                        diskStorage.saveNode(node);
//                        delete(successor.key);
//                    }
//                    return;
//                } else if (node.key > key) {
//                    parentOffset = offset;
//                    isLeftChild = true;
//                    offset = node.leftChild;
//                } else {
//                    parentOffset = offset;
//                    isLeftChild = false;
//                    offset = node.rightChild;
//                }
//            }
//        }
//
//        // 插入节点
//        public void insert(int key) {
//            int offset = rootOffset;
//            while (offset != -1) {
//                Node node = diskStorage.readNode(offset);
//                if (node.key == key) {
//                    return;
//                } else if (node.key > key) {
//                    if (node.leftChild == -1) {
//                        Node newNode = new Node(key, -1, -1, 1);
//                        diskStorage.saveNode(newNode);
//                        node.leftChild = offset + 1;
//                        diskStorage.saveNode(node);
//                        diskStorage.saveRelation(offset, offset + 1, true);
//                        rebalance(offset);
//                        return;
//                    } else {
//                        offset = node.leftChild;
//                    }
//                } else {
//                    if (node.rightChild == -1) {
//                        Node newNode = new Node(key, -1, -1, 1);
//                        diskStorage.saveNode(newNode);
//                        node.rightChild = offset + 1;
//                        diskStorage.saveNode(node);
//                        diskStorage.saveRelation(offset, offset + 1, false);
//                        rebalance(offset);
//                        return;
//                    } else {
//                        offset = node.rightChild;
//                    }
//                }
//            }
//        }
//        // 删除节点
//        public void delete(int key) {
//            int offset = rootOffset;
//            int parentOffset = -1;
//            boolean isLeftChild = false;
//            while (offset != -1) {
//                Node node = diskStorage.readNode(offset);
//                if (node.key == key) {
//                    if (node.leftChild == -1 && node.rightChild == -1) {
//                        if (parentOffset == -1) {
//                            rootOffset = -1;
//                        } else {
//                            if (isLeftChild) {
//                                diskStorage.saveRelation(parentOffset, -1, true);
//                            } else {
//                                diskStorage.saveRelation(parentOffset, -1, false);
//                            }
//                        }
//                    } else if (node.leftChild == -1) {
//                        if (parentOffset == -1) {
//                            rootOffset = node.rightChild;
//                        } else {
//                            if (isLeftChild) {
//                                diskStorage.saveRelation(parentOffset, node.rightChild, true);
//                            } else {
//                                diskStorage.saveRelation(parentOffset, node.rightChild, false);
//                            }
//                        }
//                    } else if (node.rightChild == -1) {
//                        if (parentOffset == -1) {
//                            rootOffset = node.leftChild;
//                        } else {
//                            if (isLeftChild) {
//                                diskStorage.saveRelation(parentOffset, node.leftChild, true);
//                            } else {
//                                diskStorage.saveRelation(parentOffset, node.leftChild, false);
//                            }
//                        }
//                    } else {
//                        int successorOffset = findSuccessor(node.rightChild);
//                        Node successor = diskStorage.readNode(successorOffset);
//                        node.key = successor.key;
//                        diskStorage.saveNode(node);
//                        delete(successor.key);
//                    }
//                    return;
//                } else if (node.key > key) {
//                    parentOffset = offset;
//                    isLeftChild = true;
//                    offset = node.leftChild;
//                } else {
//                    parentOffset = offset;
//                    isLeftChild = false;
//                    offset = node.rightChild;
//                }
//            }
//        }
//        // 查找后继节点
//        private int findSuccessor(int offset) {
//            Node node = diskStorage.readNode(offset);
//            while (node.leftChild != -1) {
//                node = diskStorage.readNode(node.leftChild);
//            }
//            return node.key;
//        }
//        // 平衡因子
//        private int getBalanceFactor(int offset) {
//            Node node = diskStorage.readNode(offset);
//            int leftHeight = node.leftChild == -1 ? 0 : diskStorage.readNode(node.leftChild).height;
//            int rightHeight = node.rightChild == -1 ? 0 : diskStorage.readNode(node.rightChild).height;
//            return leftHeight - rightHeight;
//        }
//        // 更新节点高度
//        private void updateHeight(int offset) {
//            Node node = diskStorage.readNode(offset);
//            int leftHeight = node.leftChild == -1 ? 0 : diskStorage.readNode(node.leftChild).height;
//            int rightHeight = node.rightChild == -1 ? 0 : diskStorage.readNode(node.rightChild).height;
//            node.height = Math.max(leftHeight, rightHeight) + 1;
//            diskStorage.saveNode(node);
//        }
//        // 左旋
//        private int leftRotate(int offset) {
//            Node node = diskStorage.readNode(offset);
//            int rightOffset = node.rightChild;
//            Node rightNode = diskStorage.readNode(rightOffset);
//            node.rightChild = rightNode.leftChild;
//            diskStorage.saveNode(node);
//            rightNode.leftChild = offset;
//            diskStorage.saveNode(rightNode);
//            updateHeight(offset);
//            updateHeight(rightOffset);
//            return rightOffset;
//        }
//        // 右旋
//        private int rightRotate(int offset) {
//            Node node = diskStorage.readNode(offset);
//            int leftOffset = node.leftChild;
//            Node leftNode = diskStorage.readNode(leftOffset);
//            node.leftChild = leftNode.rightChild;
//            diskStorage.saveNode(node);
//            leftNode.rightChild = offset;
//            diskStorage.saveNode(leftNode);
//            updateHeight(offset);
//            updateHeight(leftOffset);
//            return leftOffset;
//        }
//        // 右左旋
//        private int rightLeftRotate(int offset) {
//            int rightOffset = diskStorage.readNode(offset).rightChild;
//            diskStorage.saveNode(diskStorage.readNode(rightOffset));
//            int newOffset = rightRotate(rightOffset);
//            diskStorage.saveNode(diskStorage.readNode(newOffset));
//            return leftRotate(offset);
//        }
//        // 左右旋
//
//
//
//
//
//        // 平衡二叉树
//        private void rebalance(int offset) {
//            Node node = diskStorage.readNode(offset);
//            int balanceFactor = getBalanceFactor(offset);
//            if (balanceFactor > 1) {
//                if (getBalanceFactor(node.leftChild) < 0) {
//                    leftRotate(node.leftChild);
//                }
//                rightRotate(offset);
//            } else if (balanceFactor < -1) {
//                if (getBalanceFactor(node.rightChild) > 0) {
//                    rightRotate(node.rightChild);
//                }
//                leftRotate(offset);
//            }
//            updateHeight(offset);
//        }
//
//
//        // 查找前驱节点
//        private int findPredecessor(int offset) {
//            Node node = diskStorage.readNode(offset);
//            while (node.rightChild != -1) {
//                node = diskStorage.readNode(node.rightChild);
//            }
//            return node.key;
//        }
//
//        // 左右旋
//        private int leftRightRotate(int offset) {
//            int leftOffset = diskStorage.readNode(offset).leftChild;
//            diskStorage.saveNode(diskStorage.readNode(leftOffset));
//            int newOffset = leftRotate(leftOffset);
//            diskStorage.saveNode(diskStorage.readNode(newOffset));
//            return rightRotate(offset);
//        }
//
//
//        // 获取节点高度
//        private int getHeight(int offset) {
//            if (offset == -1) {
//                return 0;
//            }
//            Node node = diskStorage.readNode(offset);
//            return node.height;
//        }
//
//
//
//
//        // 插入节点
//        public void insert(int key) {
//            int offset = rootOffset;
//            int parentOffset = -1;
//            boolean isLeftChild = false;
//            while (offset != -1) {
//                Node node = diskStorage.readNode(offset);
//                if (node.key == key) {
//                    return;
//                } else if (node.key > key) {
//                    parentOffset = offset;
//                    isLeftChild = true;
//                    offset = node.leftChild;
//                } else {
//                    parentOffset = offset;
//                    isLeftChild = false;
//                    offset = node.rightChild;
//                }
//            }
//            Node newNode = new Node(key);
//            diskStorage.saveNode(newNode);
//            if (parentOffset == -1) {
//                rootOffset = diskStorage.getOffset(newNode);
//            } else {
//                diskStorage.saveRelation(parentOffset, diskStorage.getOffset(newNode), isLeftChild);
//            }
//            offset = rootOffset;
//            while (offset != -1) {
//                updateHeight(offset);
//                rebalance(offset);
//                Node node = diskStorage.readNode(offset);
//                if (node.parent == -1) {
//                    break;
//                }
//                offset = node.parent;
//            }
//        }
//
//
//        // 获取节点数量
//        public int size() {
//            return size(rootOffset);
//        }
//
//        private int size(int offset) {
//            if (offset == -1) {
//                return 0;
//            }
//            Node node = diskStorage.readNode(offset);
//            return size(node.leftChild) + size(node.rightChild) + 1;
//        }
//
//        // 获取最小值
//        public int getMin() {
//            int offset = rootOffset;
//            while (diskStorage.readNode(offset).leftChild != -1) {
//                offset = diskStorage.readNode(offset).leftChild;
//            }
//            return diskStorage.readNode(offset).key;
//        }
//
//        // 获取最大值
//        public int getMax() {
//            int offset = rootOffset;
//            while (diskStorage.readNode(offset).rightChild != -1) {
//                offset = diskStorage.readNode(offset).rightChild;
//            }
//            return diskStorage.readNode(offset).key;
//        }
//
//        // 删除最小值
//        public void deleteMin() {
//            int offset = rootOffset;
//            int parentOffset = -1;
//            boolean isLeftChild = false;
//            while (diskStorage.readNode(offset).leftChild != -1) {
//                parentOffset = offset;
//                isLeftChild = true;
//                offset = diskStorage.readNode(offset).leftChild;
//            }
//            delete(diskStorage.readNode(offset).key);
//        }
//
//        // 删除最大值
//        public void deleteMax() {
//            int offset = rootOffset;
//            int parentOffset = -1;
//            boolean isLeftChild = false;
//            while (diskStorage.readNode(offset).rightChild != -1) {
//                parentOffset = offset;
//                isLeftChild = false;
//                offset = diskStorage.readNode(offset).rightChild;
//            }
//            delete(diskStorage.readNode(offset).key);
//        }
//
//
//        // 删除节点
//        public void delete(int key) {
//            int offset = search(key);
//            if (offset == -1) {
//                return;
//            }
//            Node node = diskStorage.readNode(offset);
//            if (node.leftChild == -1 && node.rightChild == -1) {
//                if (node.parent == -1) {
//                    rootOffset = -1;
//                } else {
//                    diskStorage.saveRelation(node.parent, -1, node.parentLeftChild);
//                }
//            } else if (node.leftChild == -1) {
//                if (node.parent == -1) {
//                    rootOffset = node.rightChild;
//                } else {
//                    diskStorage.saveRelation(node.parent, node.rightChild, node.parentLeftChild);
//                }
//                diskStorage.saveNode(diskStorage.readNode(node.rightChild));
//            } else if (node.rightChild == -1) {
//                if (node.parent == -1) {
//                    rootOffset = node.leftChild;
//                } else {
//                    diskStorage.saveRelation(node.parent, node.leftChild, node.parentLeftChild);
//                }
//                diskStorage.saveNode(diskStorage.readNode(node.leftChild));
//            } else {
//                int predecessor = findPredecessor(node.leftChild);
//                delete(predecessor);
//                node.key = predecessor;
//                diskStorage.saveNode(node);
//            }
//            offset = rootOffset;
//            while (offset != -1) {
//                updateHeight(offset);
//                rebalance(offset);
//                Node parentNode = diskStorage.readNode(offset);
//                if (parentNode.parent == -1) {
//                    break;
//                }
//                offset = parentNode.parent;
//            }
//        }
//
//
//        // 获取节点数量
//        public int size() {
//            return size(rootOffset);
//        }
//
//        private int size(int offset) {
//            if (offset == -1) {
//                return 0;
//            }
//            Node node = diskStorage.readNode(offset);
//            return size(node.leftChild) + size(node.rightChild) + 1;
//        }
//
//        // 删除最小值
//        public void deleteMin() {
//            int offset = rootOffset;
//            int parentOffset = -1;
//            boolean isLeftChild = false;
//            while (diskStorage.readNode(offset).leftChild != -1) {
//                parentOffset = offset;
//                isLeftChild = true;
//                offset = diskStorage.readNode(offset).leftChild;
//            }
//            delete(diskStorage.readNode(offset).key);
//        }
//
//        // 删除最大值
//        public void deleteMax() {
//            int offset = rootOffset;
//            int parentOffset = -1;
//            boolean isLeftChild = false;
//            while (diskStorage.readNode(offset).rightChild != -1) {
//                parentOffset = offset;
//                isLeftChild = false;
//                offset = diskStorage.readNode(offset).rightChild;
//            }
//            delete(diskStorage.readNode(offset).key);
//        }
//
//
//
//
//        // 查找后继节点
//        private int findSuccessor(int offset) {
//            Node node = diskStorage.readNode(offset);
//            while (node.leftChild
//
//            // 平衡因子
//            private int getBalanceFactor(int offset) {
//                Node node = diskStorage.readNode(offset);
//                int leftHeight = node.leftChild == -1 ? 0 : diskStorage.readNode(node.leftChild).height;
//                int rightHeight = node.rightChild == -1 ? 0 : diskStorage.readNode(node.rightChild).height;
//                return leftHeight - rightHeight;
//            }
//            // 更新节点高度
//            private void updateHeight(int offset) {
//                Node node = diskStorage.readNode(offset);
//                int leftHeight = node.leftChild == -1 ? 0 : diskStorage.readNode(node.leftChild).height;
//                int rightHeight = node.rightChild == -1 ? 0 : diskStorage.readNode(node.rightChild).height;
//                node.height = Math.max(leftHeight, rightHeight) + 1;
//                diskStorage.saveNode(node);
//            }
//            // 左旋
//            private int leftRotate(int offset) {
//                Node node = diskStorage.readNode(offset);
//                int rightOffset = node.rightChild;
//                Node rightNode = diskStorage.readNode(rightOffset);
//                node.rightChild = rightNode.leftChild;
//                diskStorage.saveNode(node);
//                rightNode.leftChild = offset;
//                diskStorage.saveNode(rightNode);
//                updateHeight(offset);
//                updateHeight(rightOffset);
//                return rightOffset;
//            }
//            // 右旋
//            private int rightRotate(int offset) {
//                Node node = diskStorage.readNode(offset);
//                int leftOffset = node.leftChild;
//                Node leftNode = diskStorage.readNode(leftOffset);
//                node.leftChild = leftNode.rightChild;
//                diskStorage.saveNode(node);
//                leftNode.rightChild = offset;
//                diskStorage.saveNode(leftNode);
//                updateHeight(offset);
//                updateHeight(leftOffset);
//                return leftOffset;
//            }
//            // 右左旋
//            private int rightLeftRotate(int offset) {
//                int rightOffset = diskStorage.readNode(offset).rightChild;
//                diskStorage.saveNode(diskStorage.readNode(rightOffset));
//                int newOffset = rightRotate(rightOffset);
//                diskStorage.saveNode(diskStorage.readNode(newOffset));
//                return leftRotate(offset);
//            }
//            // 左右旋
//            private int leftRightRotate(int offset) {
//                int leftOffset = diskStorage.readNode(offset).leftChild;
//                diskStorage.saveNode(diskStorage.readNode(leftOffset));
//                int newOffset = leftRotate(leftOffset);
//                diskStorage.saveNode(diskStorage.readNode(newOffset));
//                return rightRotate(offset);
//            }
//            // 平衡节点
//            private void rebalance(int offset) {
//                while (offset != -1) {
//                    updateHeight(offset);
//                    int balanceFactor = getBalanceFactor(offset);
//                    if (balanceFactor > 1) {
//                        if (getBalanceFactor(diskStorage.readNode(offset).leftChild) >= 0) {
//                            offset = rightRotate(offset);
//                        } else {
//                            offset = leftRightRotate(offset);
//                        }
//                    } else if (balanceFactor < -1) {
//                        if (getBalanceFactor(diskStorage.readNode(offset).rightChild) <= 0) {
//                            offset = leftRotate(offset);
//                        } else {
//                            offset = rightLeftRotate(offset);
//                        }
//                    }
//                    int[] relation = diskStorage.readRelation(offset, true);
//                    int parentOffset = relation[0];
//                    boolean isLeftChild = relation[1] == 1;
//                    offset = parentOffset;
//                }
//            }
//
//
//            // 获取树的高度
//            public int getHeight() {
//                return getHeight(rootOffset);
//            }
//            // 获取指定节点的高度
//            private int getHeight(int offset) {
//                if (offset == -1) {
//                    return 0;
//                }
//                Node node = diskStorage.readNode(offset);
//                int leftHeight = getHeight(node.leftChild);
//                int rightHeight = getHeight(node.rightChild);
//                return Math.max(leftHeight, rightHeight) + 1;
//            }
//
//
//            // 中序遍历
//            public void inorderTraversal() {
//                inorderTraversal(rootOffset);
//            }
//            private void inorderTraversal(int offset) {
//                if (offset == -1) {
//                    return;
//                }
//                Node node = diskStorage.readNode(offset);
//                inorderTraversal(node.leftChild);
//                System.out.print(node.key + " ");
//                inorderTraversal(node.rightChild);
//            }
//
//
//            // 获取指定节点的前驱节点
//            private int findPredecessor(int offset) {
//                Node node = diskStorage.readNode(offset);
//                while (node.rightChild != -1) {
//                    node = diskStorage.readNode(node.rightChild);
//                }
//                return node.key;
//            }
//
//
//            // 获取指定节点的后继节点
//            private int findSuccessor(int offset) {
//                Node node = diskStorage.readNode(offset);
//                while (node.leftChild != -1) {
//                    node = diskStorage.readNode(node.leftChild);
//
//
//                    private
//                }
//                return node.key;
//            }
//
//
//        }
