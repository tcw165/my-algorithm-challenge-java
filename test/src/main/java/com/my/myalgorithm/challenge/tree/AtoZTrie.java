// Copyright (c) 2016-present boyw165
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.my.myalgorithm.challenge.tree;

import java.util.Arrays;
import java.util.Stack;

/**
 * A trie, also called digital tree and sometimes radix tree or prefix tree
 * (as they can be searched by prefixes), is a kind of search tree.
 * <br/>
 * <br/>
 * Complexity
 * <br/>
 * Time: average O(n), n is the length of the string.
 * <br/>
 * Space: worst O(nm) auxiliary, n is the average length of the string and
 * m is the number of the number of the strings.
 * <br/>
 * <br/>
 * Wiki: https://en.wikipedia.org/wiki/Trie
 * <br/>
 * <pre>
 *
 * 1) An empty AtoZTrie
 *       {}  <= root
 *
 * 1) add "key"
 *       {k} <= root
 *       /
 *     {e}
 *     /
 *   {y}
 *   /
 * {}
 *
 * 2) add "word"
 *       {k,w} <= root
 *       /  \
 *     {e}  {o}
 *     /     \
 *   {y}     {r}
 *   /        \
 * {}         {d}
 *             \
 *             {}
 *
 * 3) add "keyword"
 *                {k,w} <= root
 *                /  \
 *              {e}  {o}
 *              /     \
 *            {y}     {r}
 *            /        \
 *          {w}        {d}
 *          /           \
 *        {o}           {}
 *        /
 *      {r}
 *      /
 *    {d}
 *
 * 4) add "keyboard"
 *                {k,w} <= root
 *                /  \
 *              {e}  {o}
 *              /     \
 *            {y}     {r}
 *            /\       \
 *          {w}{b}     {d}
 *          /   \       \
 *        {o}   {o}     {}
 *        /      \
 *      {r}      {a}
 *      /         \
 *    {d}         {r}
 *                 \
 *                 {d}
 *
 * </pre>
 */
public class AtoZTrie {

    private Node mRoot = new Node(null);

    public void insert(String word) {
        if (word == null || word.length() == 0) return;

        // To lower case.
        word = word.toLowerCase();
        Node node = mRoot;
        int i = 0;
        while (i < word.length()) {
            char c = word.charAt(i++);
            if (c >= 'a' && c <= 'z') {
                int j = c - 'a';
                Node childNode = node.children[j];

                if (childNode == null) {
                    // Add the key to the node.
                    childNode = node.children[j] = new Node(node);
                }

                node = childNode;
            } else {
                throw new IllegalArgumentException("The word contains illegal character.");
            }
        }

        node.endOfWord = true;
    }

    public void delete(String word) {
        // TODO: Complete it.
    }

    /**
     * Return true if lookups matches the word completely.
     */
    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;

        // To lower case.
        word = word.toLowerCase();
        // Prepare the visited stack, the size of the stack indicates the
        // letter position.
        Stack<NodeExtra> visited = new Stack<>();
        visited.add(new NodeExtra(mRoot));
        // Start the Depth-First-Searching.
        while (!visited.isEmpty() && visited.size() <= word.length()) {
            NodeExtra now = visited.lastElement();

            // Search by going down.
            char c = word.charAt(visited.size() - 1);
            int i = c - 'a';
            boolean ifFoundChild = false;
            if (c >= 'a' && c <= 'z' &&
                i > now.visitedChildIndex &&
                now.node.children[i] != null) {
                // Keep going down when the child is present.
                visited.push(new NodeExtra(now.node.children[i]));
                // Update the number of visited children.
                now.visitedChildIndex = i;
                ifFoundChild = true;
            } else if (c == '.') {
                // Keep going down when the it is a wildcard searching.
                // TODO: Improvement with binary-search?
                for (i = now.visitedChildIndex + 1;
                     i < now.node.children.length;
                     ++i) {
                    // Update the visited index.
                    now.visitedChildIndex = i;
                    if (now.node.children[i] != null) {
                        visited.push(new NodeExtra(now.node.children[i]));
                        // Update the number of visited children.
                        ifFoundChild = true;
                        break;
                    }
                }
            }

            // Match the string or pattern when the traversal reaches the bottom?
            if (visited.size() == word.length() + 1) {
                if (visited.lastElement().node.endOfWord) {
                    return true;
                } else {
                    ifFoundChild = false;
                }
            }

            // Going up if the last letter is a wildcard character, so that it
            // could keep searching for other possibility.
            if (!ifFoundChild) {
                visited.pop();
            }
        }

        return false;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) return false;

        Node node = mRoot;
        int i = 0;
        while (node != null && i < prefix.length()) {
            char c = prefix.charAt(i++);
            if (c >= 'a' && c <= 'z') {
                node = node.children[c - 'a'];
            } else if (c == '.') {

            }
        }

        return node != null &&
               i == prefix.length();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    private static class Node {

        Node parent = null;
        Node[] children = new Node['z' - 'a' + 1];
        boolean endOfWord = false;

        Node(Node parent) {
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (endOfWord != node.endOfWord) return false;
            if (parent != null ? !parent.equals(node.parent) : node.parent != null) return false;

            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            return Arrays.equals(children, node.children);
        }

        @Override
        public int hashCode() {
            int result = parent != null ? parent.hashCode() : 0;
            result = 31 * result + Arrays.hashCode(children);
            result = 31 * result + (endOfWord ? 1 : 0);
            return result;
        }
    }

    private static class NodeExtra {

        Node node = null;
        Integer visitedChildIndex = -1;

        public NodeExtra(Node node) {
            this.node = node;
        }
    }
}
