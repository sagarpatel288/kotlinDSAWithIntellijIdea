# Tree Data Structure

## Table Of Contents

- [What does it represent?](#what-does-it-represent)
- [Terminologies](#terminologies)
- [Forest](#forest)
- [Tree Definition](#tree-definition)
- [A Degenerate Or Pathological Tree](#a-degenerate-or-pathological-tree)
- [Can a tree have a cycle?](#can-a-tree-have-a-cycle)
- [Nodes at level `l`](#what-are-the-maximum-number-of-nodes-at-level-l-in-a-binary-tree)
- [Real-world examples of tree data structure](#real-world-examples-where-the-tree-data-structure-is-used)
- [Tree Traversal](#tree-traversal)

## What does it represent?

- [A Sentence Structure](#a-sentence-structure)
- [A Syntax Tree For A Mathematical Expression](#a-syntax-tree-for-a-mathematical-expression)
- [Geographical Hierarchy](#geographical-hierarchy)
- [Categorization or Classification (Types & Subtypes)](#categorization-classification-types-and-subtypes-etc)
- [Abstract Syntax Tree For Code](#abstract-syntax-tree-for-code)
- [Binary Search Tree](#binary-search-tree)

### A Sentence Structure

```

             I ate the plum                     
                                            
                                            
                                            
            Sentense                        
                                            
             /   \                          
         1  /     \ 2                       
           /       \                        
          /         \                       
                                            
  Noun Phrase    Verb Phrase                
                                            
   │              /        \                
1  │          1  /          \ 2             
   │            /            \              
   │           /              \             
                                            
   I        Verb          Noun Phrase       
                                            
             │             /        \       
          1  │         1  /          \  2   
             │           /            \     
             │          /              \    
                                            
            ate      Determiner       Noun  
                                            
                        │               │   
                        │               │   
                     1  │               │  1
                        │               │   
                                            
                       the             plum 


```

1. We have this sentence: I ate the plum
2. A sentence can be represented as a grammatical structure.
3. Our sentence has two main parts: Noun Phrase and Verb Phrase.
4. The Noun Phrase has a child, "I".
5. The Verb Phrase has two main parts: Verb and Noun Phrase.
6. The Verb is: ate
7. The noun phrase has two main parts: determiner and noun.
8. The determiner has one child: the
9. The noun has one child: plum
10. So, we represented a sentence structure using a tree.
11. Hence, we can use a tree to represent a structure.

### A Syntax Tree For A Mathematical Expression

```

   2 Sin(3z - 7)        
                        
                        
                        
                        
        x               
                        
      /   \             
  1  /     \ 2          
    /       \           
   /         \          
                        
  2          sin        
                        
              │         
           1  │         
              │         
              │         
                        
              -         
          /        \    
      1  /          \  2
        /            \  
       /              \ 
                        
      x                7
                        
    /   \               
1  /     \  2           
  /       \             
 /         \            
                        
3           z           

```

1. We have this expression: `2 sin(3z - 7)`
2. So, there is a final multiplication between `2` and `sin(3z - 7)`
3. Hence, we can consider multiplication at the root as a final destination (the last operation).
4. So, the root multiplication has two children: `2` and `sin(3z - 7)`.
5. Inside the sine, we have a subtraction between `3z` and `7`.
6. So, sin has one child "minus" and "minus" has two children: `3z` and `7`.
7. 3z is a multiplication between `3` and `z`.
8. If we notice, we keep branching until there are no more children.
9. And if we travel from the bottom to top, we get the original expression.
10. For example, we have a multiplication between `3` and `z` at the bottom. So, it is `3z`.
11. Then, we have a subtraction between `3z` and `7`. So, it becomes `3z - 7`.
12. Then, we have the sine of `3z - 7`. So, it becomes `sin(3z - 7)`.
13. Then, we have multiplication between 2 and the sine value. So, it becomes `2 sin(3z - 7)`, which is the original expression.
14. So, this is how we can use a syntax tree to represent a mathematical expression.

### Geographical Hierarchy

```

Geography Hierarchy

World
├── United States
│   ├── Wyoming
│   │   ├── Jackson
│   │   └── Cheyenne
│   ├── ...
│   └── Alabama
│       ├── Montgomery
│       └── Mobile
├── ...
└── United Kingdom
    ├── Wales
    ├── Scotland
    ├── Northern Ireland
    └── England


```

```

                                        Jackson   
                                                  
                                       /          
                                      /           
                                     /            
                                    /             
                            Wyoming               
                                    \             
                             /       \            
                            /         \           
                           /           \          
                          /                       
                         /              Cheyenne  
           United States    •••••••               
                         \              Montgomery
               /          \                       
              /            \            /         
             /              \          /          
            /                \        /           
           /                         /            
          /                 Alabama               
   World   ••••••••••                \            
          \                           \           
           \                           \          
            \                           \         
             \                                    
              \                         Mobile    
               \                                  
                \                                 
                                                  
          United Kingdom                          
                                                  
         /    / •  \    \                         
        /    /  •   \    \                        
       /    /   •    \    \                       
      /    /    •     \    \                      
     /    /     •      \    \                     
         /              \                         
Wales   /                \   England              
       /                  \                       
      /                    \                      
     /                      \                     
                                                  
 Scotland            Northern Ireland             

```

1. We can represent a hierarchy also.
2. For example, we can have `world` as a root.
3. And then, we can have various `countries` as children. For example, United States and United Kingdom.
4. And then each country can have various `states` within. For example, the United States have various `states` such as Wyoming, Alabama, etc.
5. And then each state can have various `cities` in it. For example, Wyoming has Jackson, Cheyenne, etc.
6. So, we can use a tree data structure to represent a hierarchy.

### Categorization (Classification, Types and Subtypes, etc.)

```

                                Arachnid               
                               /                       
                              /                        
                             /                         
                            /                          
                           /                           
              Invertebrate  ••••••••                   
                           \               Butterflies 
            /               \             /            
           /                 \           /             
          /   Reptile         \         /              
         /                     \       /               
        /    /                  Insect ────────── Flies
       /    /                          \               
      /    /                            \              
     /    /      Amphibian               \             
    /    /                                \            
   /    /       /                          Beetles     
  /    /       /                                       
 /    /       /                                        
             /                                         
A n i m a l   ••••••••••                               
             \                                         
 \    \       \                                        
  \    \       \                                       
   \    \       \                                      
    \    \       Bird                                  
     \    \                                            
      \    \                                           
       \    Fish       Rodents                        
        \             /                                
         \           /                                 
          \         /   Carnivores                     
           \       /   /                               
            \     /   /                                
             \   /   /                                 
                    /                                  
             Mammal ••••••••••                         
                    \                                  
                \    \                                 
                 \    \                                
                  \    \                               
                   \    Primates                       
                    \                                  
                     \                                 
                      \                                
                       Marsupials                      



```

1. We can use a tree to represent categorization, classification, types and subtypes, etc.
2. For example, we can use a tree to represent the Animal classification (types and subtypes).

### Abstract Syntax Tree For Code

```

while (x < 0) {
    x = x + 2
    foo(x)
} 

                          while
                         /     \
                        /       \
            compare op: <         block
              /        \         /     \
             /          \       /       \
         var: x       const: 0 assign   procedure call
                               /    \           /      \
                              /      \         /        \
                          var: x   binop: +  var: foo   var: x
                                    /   \
                                   /     \
                               var: x   const: 2


```

1. To represent a code, we can use a tree.
2. For example, we have a while loop. So, `while` is a root of the tree.
3. Then, we have a `condition` to continue the while loop and the `block` (logic, body).
4. So, the node `while` has two children: The `condition` and the `block`.
5. The `condition` has a comparison operator `<` between `x` and `0`.
6. It means, the `condition` uses the comparison operator `<`, and it has two children: `x` and `0`.
7. Next, we have the `block`. The `block` has two operations. `Assignment` and `function call (= procedure call)`.
8. It means, the `block` has two children: `Assignment` and `Procedure Call`.
9. The `assignment` has left side `x` and right side `x + 2`.
10. It means, the `assignment` node has two children: `x` and `binary operator +`.
11. The node `binary operator +` is applied between `x` and `2`.
12. It means, the `binary operator +` has two children: `x` and `2`.
13. For the `Procedure Call,` we have the function `foo` for which we pass the argument `x`.
14. It means, the `procedure call` has two children: The function call `foo` and the argument `x`.
15. So, this is how we can use an abstract syntax tree to represent a code.

### Binary Search Tree

```
                        Les
                       /   \
                      /     \
                     /       \
                Cathy         Sam
                /   \         /   \
               /     \       /     \
           Alex    Frank  Nancy   Violet
                                   /    \
                                  /      \
                               Tony     Wendy

```

```

              /\          
             /  \         
            /    \        
           /      \       
          /        \      
         /          \     
        /            \    
\      /              \   
 \    /                \  
  \  /                  \ 
   \/                    \


```

* We can use a binary tree to represent relationships between nodes where a node cannot have more than two children.
* Here, the node is greater than the left side children and smaller than the right side children.
* For example, in the given ASCII diagram, the root `Les` comes after (greater than) the left side child, `Cathy`, and comes before (less than) the right side child, `Sam`.
* We can say it another way like: The left side children are smaller (comes before) than the node.
* Similarly, the node `Les` comes before (smaller than) the right side child, `Sam`.
* Again, we can say it other way like: The right side children are greater (comes after) than the node.
* To remember this relationship, we can use the diagram given below the binary search tree.
* So, it starts with the `greater than` symbol on the left side, moves towards the `root`, and goes down to form the `less than` symbol on the right side.
* **It signifies that the `node` is `greater` than the `left side`, and `smaller` than the `right side`.**
* These predefined rules help us find a child quickly.
* For example, if we want to find `Tony` and we start from the root node, `Les`.
* We know that `Tony` comes after `Les`. Hence, we need to travel to the right side of the `Les`.
* Now, we know that `Tony` comes after `Sam`. So, we go towards the right side of `Sam`.
* Now, we know that `Tony` comes before `Violet`.  So, we go towards the left side of the `Violet` and we find the target: `Tony`.

## Terminologies:

```
                        Les
                       /   \
                      /     \
                     /       \
                Cathy         Sam
                /   \         /   \
               /     \       /     \
           Alex    Frank  Nancy   Violet
                                   /    \
                                  /      \
                               Tony     Wendy

```

Let us also understand a few terminologies.

### `Node`: 

1. A node has a key, an optional parent, and an optional child or children. 
2. If it does not have a parent node, it is called the `Root Node`. 
3. If it does not have any children, it is called a `Leaf` node.

### `Root Node`:

1. A node that does not have any parent. 
2. The top node of the tree is called the `Root Node`.

### `Key`: 

1. Each node has a key. For example, the node `Les` has the key, `Les`. 
2. Similarly, the node `Wendy` has the key `Wendy`. 
3. A `Key` is a value of a node that we can use to refer a node.

### `Parent`: 

1. Each node has one parent except the root node. 
2. A parent node is a node directly connected with the target node through an edge. 
3. It is just above the target node. 
4. For example, `Cathy` is a parent for `Alex` and `Frank`. 
5. Similarly, `Violet` is a parent for `Tony` and `Wendy`.

### `Children`: 

1. A child node is a node directly connected with a target node through an edge, just below the target node. 
2. Each node can have one or more children. 
3. For a `Binary Search Tree`, a node can have `maximum two children`. 
4. For example, the node `Les` has two direct children: `Cathy` and `Sam`. 
5. Similarly, the node `Violet` has two children: `Tony` and `Wendy`.

### `Siblings`: 

1. All the nodes that has the same parent node, are `siblings` of each other.

### `Ancestor`: 

1. For a particular node, its parent node, the parent's parent node, and so on until we reach to the root node are called `ancestors` of the node. 
2. For example, `Cathy` and `Les` are `ancestors` of `Alex`.

### `Proper Ancestor`:

1. When we do not include the target node itself as an `ancestor` of itself, the remaining `ancestors` are called `Proper Ancestor` of the node.
2. For example, `Sam` and `Les` are `ancestors` of `Sam`. But only `Les` is a `Proper Ancestor` of `Sam`. 

### `Descendants`: 

1. For a particular node, its children node, the children's children node, and so on until we reach to the bottom of the tree after which there are no more children, are called `descendants` of the node. 
2. For example, `Cathy`, `Alex`, `Tony`, (basically, all the nodes) are `descendants` of the root node, `Les`.
3. `Tony` and `Wendy` are `descendants` of `Sam`.
4. `Alex` and `Frank` are `descendants` of `Les`.

### `Proper Descendants`:

1. When we do not include the node itself as a `descendant` of itself, the remaining `descendants` are called `Proper Descendants` of the node.
2. For example, `Violet`, `Tony`, and `Wendy` are `descendants` of `Violet`. But, only `Tony` and `Wendy` are `proper descendants` of `Violet`. 

### `Subtree`:

1. Any node and all its descendants form a subtree.
2. For example, `Sam` and its descendants form a subtree.

### `Leaf`: 

1. A node that does not have any children is called a `Leaf`. 
2. For example, `Alex`, `Frank`, `Nancy`, `Tony`, and `Wendy`.

### `Internal Nodes` or `Interior Nodes`:

1. All the nodes that have children are called `Internal Nodes` or `Interior Nodes`. 
2. So, `Les`, `Cathy`, `Sam`, and `Violet` are `Internal Nodes`. 
3. Another way to remember it is, all the nodes that are `non-leaf`, are called `Internal Nodes`.

### `Height`: 

1. How far down from the target node? The answer gives us height for the node. 
2. The distance between the target node and the leaf is height of the target node. 
3. To calculate height of a particular node, we go downwards. 
4. We can also say that the height of a particular node is number of edges between the node and the leaf. 
5. For example, there are two edges between the node `Sam` and the farthest down leaf node, `Tony` or `Wendy`. 
6. Hence, the height of the node, `Sam` is 2. 
7. Similarly, the height of the node `Cathy` is 1 because there is only 1 edge between the node `Cathy` and the farthest down, longest path down to a leaf node (`Alex` or `Frank`).

### `Depth`: 

1. Number of edges between the root node and the target node. 
2. Here, we go upside towards the root node. 
3. For example, the depth of the leaf node, `Tony` is `3` because there are 3 `edges` between the target node, `Tony` and the root node, `Les`.

### `Level`: 

1. If we consider that the root node is at level 1, then level of any node is `1 + Number of edges between the root and the target node`. 
2. However, `Number of edges between the root node and the target node = Depth`. So, `Level = 1 + Depth`. 
3. For example, the level of the target node, `Frank`, is `1 + Depth` = `1 + Number of edges between the root node and the target node` = `1 + 2` = `3`. 
4. The level of the root node, `Les` is 1, because it is the root node. 
5. However, some resources use 0-based level where the root node is considered at level 0. 
6. In that case, a level of any node is equal to the number of edges between the root and the target node. 
7. In short, `Level` is either `1 + depth` or equal to `depth`.

### `Size`: 

1. The size of a tree is equal to (defined as) the `total number of nodes` in the tree.
2. We calculate it recursively as: `1 (the size of the current node) + the size of the left-tree + the size of the right-tree`.

## How to remember the difference between the height, the depth, and the level? 

### `Height`: 

1. Imagine that we are at the 5th floor of a building, and we want to see the height between the 5th floor and the ground. 
2. So, we look at the downside, towards the ground to understand the height.
3. Number of edges from the target node to the leaf node.

### `Depth`: 

1. Imagine we are at the bottom of a well. 
2. Now, we look upside to learn how deep we have come.
3. Number of edges from the root node to the target node.

### `Level`:

1. Either `1 + Depth` or `Depth`.
2. If we consider root level `1`, then it is `1 + Depth`.
3. If we consider root level `0`, then it is `0 + Depth` = `Depth`.

## Forest

```
        Kate                   Sally
      /      \                   ╷  
     /        \                  ╷  
    /          \                 ╷  
   /            \                ╷  
  /              \               ╷  
                                    
Sam             Hugh            Jim 


```

* Multiple root nodes form a forest, where each root node represents an individual tree.
* A forest is a collection of multiple disjoint trees.

## Tree Definition

1. A tree is a hierarchical data structure that contains one or more nodes where each node must have a key, an optional parent, and an optional child or children nodes.
2. A tree is an acyclic data structure. It means, a tree cannot have a cycle.
3. If a tree has a cycle, it becomes a graph data structure.

## A Degenerate or Pathological Tree

1. A degenerate tree is a tree where each parent has only one child, making it a linked list.

## Can a tree have a cycle?

1. No. A tree cannot have a cycle by definition.
2. A tree with a cycle becomes a graph data structure.

## What are the maximum number of nodes at level `l` in a binary tree?

1. $2^l$ (2 raised to the power of l, where root level is considered as 0).

## Real-world examples where the tree data structure is used.

1. File Systems (Directory Structure).
2. HTML / XML DOM (Document Object Model) Trees (Tags inside Tags).
3. Organization Charts.
4. Database Indexing (B-trees).
5. Expression Parsing (Syntax trees).
6. Routing Algorithms (trie, prefix trees).

## Tree Traversal

- [Depth First](#depth-first)
- [Breadth Search](#breadth-search)
- [In-Order (Left-Root-Right)](#in-order-left-root-right)
- [Pre-Order (Root-Left-Right)](#pre-order-root-left-right)
- [Post-Order (Left-Right-Root)](#post-order-left-right-root)

```mermaid
---
config:
  theme: redux
  flowchart:
    curve: linear
---
flowchart TD
    A(("A")) --- B(("B")) & C(("C"))
    B --- n1(("<br>")):::invis
    B --- D(("D"))
    C --- E(("E")) & F(("F"))
    E --- G(("G"))
    E --- n6(("<br>")):::invis
    F --- H(("H"))
    F --- I(("I"))
    style n1 stroke:none
    style n6 stroke:none
    linkStyle 2 stroke:none,fill:none
    linkStyle 7 stroke:none,fill:none
    classDef invis opacity:0;
```

### Depth-First

1. We completely travel and finish one subtree before starting a sibling traversal.
2. For example, we may travel A-B-D (finishing one subtree) followed by C-E-G, F-H, and I.
3. However, there are 3 different ways (rules, orders) to finish one subtree.
4. `In-Order`, `Pre-Order`, and `Post-Order`.
5. As we know, the main parts of a node are the `Root`, `Left`, and `Right` sides.
6. These order names indicate the order of the `Root` part.
7. For example, the `In-Order` sequence for the travelling is: `Left-Root-Right`. So, the `Root` comes inside `Left` and `Right`.
8. Then, we have the `Pre-Order` sequence where the `Root` comes before the `Left` and the `Right` sides. So, it becomes: `Root-Left-Right`.
9. Finally, we have the `Post-Order` sequence where the `Root` comes after the `Left` and the `Right` sides. So, it becomes `Left-Right-Root`.
10. We will study each order in detail.

### Breadth-Search

1. Here, we travel level-by-level.
2. So, first we finish travelling with all the siblings.
3. And then, we gradually move towards the last level.
4. For example, we may travel in this order: A-B-C-D-E-F-G-H-I.

#### In-Order (Left-Root-Right)

##### Resources / References:

[Jenny's Lecture](https://youtu.be/-b2lciNd2L4?si=tB3crtV88O9ncEuu)

```mermaid
---
config:
  theme: redux
  flowchart:
    curve: linear
---
flowchart TD
    A(("A")) --- B(("B")) & C(("C"))
    B --- n1(("<br>")):::invis
    B --- D(("D"))
    C --- E(("E")) & F(("F"))
    E --- G(("G"))
    E --- n6(("<br>")):::invis
    F --- H(("H"))
    F --- I(("I"))
    style n1 stroke:none
    style n6 stroke:none
    linkStyle 2 stroke:none,fill:none
    linkStyle 7 stroke:none,fill:none
    classDef invis opacity:0;
```

* `Root` comes inside the `Left` and the `Right` sides.
* So, the order becomes: `Left-Root-Right`.
* Hence, the `in-order` traversal for the given example will be as below:
* We start with the root node, `A`. <------------------------------ 
* Cover the left side of `A`. <------------------------------
* The `in-order` sequence prioritizes the `Left` side first, before the `Root` part.
* So, we continue the depth traversal towards the left side of `A`.
* The root node `A` has two children. `B` and `C`.
* The `in-order` sequence prioritizes the `left` part first.
* So, we traverse through the left side, `B`.
* We get `B`.
* Cover the left side of `B`. <------------------------------ 
* But, `B` does not have the left side. 
* Cover the root, `B`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, now we can consider `B`. ------------------------------------------------------------------------------(1).
* Node `B` does not have the `Left` side. So, we considered `B`. 
* Now, cover the right side of `B`. <------------------------------
* So, now we consider the right side of `B`, which is `D`.
* We get `D` - Cover the left side of it. <------------------------------ 
* But it does not have a left side.
* Cover the root, `D`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, it becomes: `B -- D`. ------------------------------------------------------------------------------(2).
* Cover the right side of `D`. <------------------------------ 
* But, it does not have a right side.
* Then, as the `Right` side of `B` is finished, we can move backward.
* The subtree `B` is covered. So, we move backward (upward).
* The subtree `B` was the `Left` side of the `Root` node, `A`. <------------------------------
* It means we have finished the `Left` part of the `Left-Root-Right` for the `Root` node, `A`.
* So, now comes the turn of the `Root` node, `A`. <------------------------------
* Hence, it becomes: `B -- D -- A`. -------------------------------------------------------------------------(3).
* For the root node `A`, we have covered the `Left-Root` parts of the `Left-Root-Right` sequence.
* Now, we need to cover the `Right` side of the root node, `A`.
* We get `C` on the right side of `A`.
* So, we start with `C`. Cover the left side of `C`. <------------------------------
* We know that we need to take the left side before taking the root. Right?
* So, we travel towards the left side of the `C`.
* We get `E`. Cover the left side of `E`. <------------------------------
* So, we travel to the left side of `E`. 
* We get `G`. Cover the left side of `G`. <------------------------------ 
* But it does not have a left side. 
* So, cover the root, `G`. (It is a root of a subtree that starts with itself.) <------------------------------
* Hence, it becomes: `B -- D -- A -- G`. --------------------------------------------------------------------(4).
* Cover the right side of `G`. <------------------------------
* `G` does not have any children. So, we move backward (upward).
* We get `E`. Can we take it? Yes. Why? Because we have already covered its left side.
* Cover the root, `E`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, now it becomes: `B -- D -- A -- G -- E`. --------------------------------------------------------------(5).
* Cover the right side of `E`. <------------------------------
* Does `E` have the right side? No.
* So, we move backward (upward).
* We get `C`. Should we take it? Yes. Why? Because we have already covered the left side of `C`.
* Now, cover the root, `C`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, it becomes: `B -- D -- A -- G -- E -- C`. -------------------------------------------------------------(6).
* What is next? We have covered the left side of `C`, the root `C`, and now, we need to cover the right side of `C`.
* Cover the right side of `C`. <------------------------------
* So, we travel to the right side of `C`.
* We get `F`. Should we take it? Well, does it have a left side? If yes, the left side will get the priority.
* Cover the left side of `F`. <------------------------------
* We get `H`. Should we take it? Well, does it have a left side? If not, then only we can take it.
* Cover the left side of `H`. <------------------------------
* `H` does not have a left side. So, we can take it.
* So, it becomes: `B -- D -- A -- G -- E -- C -- H`. --------------------------------------------------------(7).
* What is next? Does `H` have a right side? No. So, we can move backward (upward).
* Cover the root of `H`. <------------------------------
* We get `F`. Should we take it? Well, did we cover the left side of it? Yes. Then, we can take it.
* Cover the root, `F`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, it becomes: `B -- D -- A -- G -- E -- C -- H -- F`. ----------------------------------------------(8).
* What is next? Did we cover the right side of `F`? No. So, let us cover it.
* Cover the right side of `F`. <------------------------------
* We get `I`. Should we take it? Well, did we cover the left side of it? 
* Cover the left side of `I`.
* `I` - It does not have a left side. Then, we can take it.
* Cover the root, `I`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, it becomes: `B -- D -- A -- G -- E -- C -- H -- F -- I`. -----------------------------------------(9).
* What is next? Did we cover the right of `I`? 
* Cover the right side of `I`. <------------------------------ 
* Well, it does not have any. So, we can move backward (upward).
* We have already taken F, C, and A.
* All the subtrees have been covered. The entire tree has been covered.
* We travelled and covered one subtree after another.
* And the path we travelled is:
* `B -- D -- A -- G -- E -- C -- H -- F -- I`. ----------------------------------------------(10).

##### Important Key Points:

Resources / References:

[Coursera's UC San Diego Course: Data Structures: Module 01: Section 03: Trees: Video 02: Timestamp: 02:44](https://coursera.org/share/e9f19723cdd15d5e4f0abe3c8c87fe34)

###### The `In-Order` Traversal Of A Binary Tree Gives A Sorted Order Output:

```
                        Les
                       /   \
                      /     \
                     /       \
                Cathy         Sam
                /   \         /   \
               /     \       /     \
           Alex    Frank  Nancy   Violet
                                   /    \
                                  /      \
                               Tony     Wendy


Output: Alex, Cathy, Frank, Les, Nancy, Sam, Tony, Violet, Wendy
```

* By definition, the `In-Order` tree traversal for a binary tree gives a sorted order output.
* Why? How? Because, the [definition](#binary-search-tree) of a binary tree says that: 
* **A node is greater than the left side children, and less than the right side children.**
* So, if we travel a binary tree as `Left-Root-Right`, it matches with the `sorted order` arrangement of the binary tree.
* Hence, if we ever want to perform `sorted order` on a binary tree, we go with the `In-order` traversal.

###### Pseudocode Of `In-Order` Traversal:

```kotlin

fun <T> inOrderTraversal(key: T) {
    coverEdgeCases(key)
    inOrderTraversal(key.left)
    inOrderTraversal(key)
    inOrderTraversal(key.right)
}
```

#### Pre-Order (Root-Left-Right)

##### Resources / References:

[Jenny's Lecture](https://youtu.be/-b2lciNd2L4?si=tB3crtV88O9ncEuu)

```mermaid
---
config:
  theme: redux
  flowchart:
    curve: linear
---
flowchart TD
    A(("A")) --- B(("B")) & C(("C"))
    B --- n1(("<br>")):::invis
    B --- D(("D"))
    C --- E(("E")) & F(("F"))
    E --- G(("G"))
    E --- n6(("<br>")):::invis
    F --- H(("H"))
    F --- I(("I"))
    style n1 stroke:none
    style n6 stroke:none
    linkStyle 2 stroke:none,fill:none
    linkStyle 7 stroke:none,fill:none
    classDef invis opacity:0;
```

* For `Pre-Order` traversal, the `Root` gets the first priority, followed by the `Left` and `Right` sides.
* So, for the given example, we start with the root, `A`.
* `A` - Is it a root? Yes. So, we take it.
* Cover the root, `A`. <------------------------------
* So, it becomes `A`. --------------------------------------------------------------(1).
* Then, we ask: What is the left side of `A`?
* Cover the left side of `A`. <------------------------------
* We get `B`. Is it a root? Yes. So, we take it.
* Cover the root, `B`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, it becomes `A -- B`. --------------------------------------------------------------(2).
* Then what? We ask: Did we cover the left side of `B`?
* Cover the left side of `B`. <------------------------------
* Well, there is no left side of `B`.
* Next: Cover the right side of `B`. <------------------------------
* We get `D`. 
* Cover the root, `D`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, it becomes:
* `A -- B -- D` --------------------------------------------------------------(3).
* We finished the subtree, `D`. So, we move backward (upward).
* We get `B`. 
* We have also covered the subtree `B`. 
* So, we continue moving backward (upward).
* We get `A`.
* We have covered the left side and the root (`A` itself).
* Now, cover the right side of `A`. <------------------------------
* We get `C`.
* Cover the root, `C`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, it becomes:
* `A -- B -- D -- C` --------------------------------------------------------------(4).
* Cover the left side of the root, `C`.
* We get `E`. It is the root of a subtree that starts with itself.
* So, cover the root, `E`. (It is a root of a subtree that starts with itself.) <------------------------------
* Hence, it becomes:
* `A -- B -- D -- C -- E` --------------------------------------------------------------(5).
* Cover the left side of `E`. <------------------------------
* We get `G`.
* Cover the root, `G`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, it becomes:
* `A -- B -- D -- C -- E -- G` --------------------------------------------------------------(6).
* Cover the left side of `G`. <------------------------------ 
* But, there is no left side of `G`.
* Cover the right side of `G`. <------------------------------
* But, there is no right side of `G`.
* We have covered the subtree, `G`.
* Move backward (upward).
* We get `E`. The subtree `G` was the left side of `E`.
* Cover the right side of `E`. <------------------------------
* There is no right side of `E`.
* We have covered the subtree, `E`.
* Move backward (upward).
* We get `C`. The subtree `E` was the left side of `C`.
* Cover the right side of `C`. <------------------------------
* We get `F`.
* It is the root for a subtree that starts with itself.
* So, cover `F`.
* Hence, it becomes:
* `A -- B -- D -- C -- E -- G -- F` --------------------------------------------------------------(7).
* Cover the left side of `F`. <------------------------------
* We get `H`. (It is a root of a subtree that starts with itself.) <------------------------------
* Include `H`.
* So, it becomes:
* `A -- B -- D -- C -- E -- G -- F -- H` --------------------------------------------------------------(8).
* Cover the left side of `H`. <------------------------------
* There is no left side of `H`.
* Cover the right side of `H`. <------------------------------
* There is no right side of `H`.
* We have covered the subtree, `H`.
* So, we move backward (upward).
* We get `F`.
* We have covered the left side of `F`.
* Now, cover the right side of `F`.
* We get `I`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, we add it to our path.
* Hence, it becomes:
* `A -- B -- D -- C -- E -- G -- F -- H -- I` --------------------------------------------------------------(9).
* Cover the left side of `I`. <------------------------------
* But there is no left side of `I`.
* Cover the right side of `I`. <------------------------------
* But there is no right side of `I`.
* We have covered the subtree, `I`.
* So, we move backward (upward).
* We get `F`. We have already covered the subtree, `F`.
* So, we move backward (upward).
* We get `C`. We have already covered the subtree, `C`.
* So, we move backward (upward).
* We get `A`. We have already covered the tree, `A`.
* So, the tree traversal path is:
* `A -- B -- D -- C -- E -- G -- F -- H -- I` --------------------------------------------------------------(10). 

#### Post-Order (Left-Right-Root)

##### Resources / References:

[Jenny's Lecture](https://youtu.be/-b2lciNd2L4?si=tB3crtV88O9ncEuu)

```mermaid
---
config:
  theme: redux
  flowchart:
    curve: linear
---
flowchart TD
    A(("A")) --- B(("B")) & C(("C"))
    B --- n1(("<br>")):::invis
    B --- D(("D"))
    C --- E(("E")) & F(("F"))
    E --- G(("G"))
    E --- n6(("<br>")):::invis
    F --- H(("H"))
    F --- I(("I"))
    style n1 stroke:none
    style n6 stroke:none
    linkStyle 2 stroke:none,fill:none
    linkStyle 7 stroke:none,fill:none
    classDef invis opacity:0;
```

* In `Post-Order`, the priority order is: `Left-Right-Root`.
* Let us start with the root node, `A`. <------------------------------
* Cover the left side of `A`. <------------------------------
* We get `B`.
* Cover the left side of `B`. <------------------------------
* There is no left side of `B`.
* Cover the right side of `B`. <------------------------------
* We get `D`. 
* Cover the left side of `D`. <------------------------------
* There is no left side of `D`.
* Cover the right side of `D`. <------------------------------
* There is no right side of `D`.
* Cover `D`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, it becomes:
* `D` ------------------------------------------------------(1)
* We have covered the subtree, `D`.
* So, we move backward (upward).
* We get `B`.
* We have already covered the left and right sides of `B`.
* That's why and how we are back to `B`.
* So now, let us cover `B`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, it becomes:
* `D -- B` ------------------------------------------------------(2)
* We have covered the subtree, `B`.
* So, we move backward (upward).
* We get `A`.
* We have covered the left side of `A`.
* Now, let us cover the right side of `A`. <------------------------------
* We get `C`.
* Let us cover the left side of `C`. <------------------------------
* We get `E`.
* Let us cover the left side of `E`. <------------------------------
* We get `G`.
* Let us cover the left side of `G`. <------------------------------
* There is no left side of `G`.
* Let us cover the right side of `G`. <------------------------------
* There is no right side of `G`.
* Let us cover the root, `G`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, the path becomes:
* `D -- B -- G` ------------------------------------------------------(3)
* We have covered the subtree, `G`.
* So, we move backward (upward).
* We get `E`.
* We have covered the left side of `E`.
* Let us cover the right side of `E`. <------------------------------
* There is no right side of `E`.
* Then, let us cover the root, `E`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, the path becomes:
* `D -- B -- G -- E` ------------------------------------------------------(4) 
* We have covered the subtree, `E`.
* Now, we move backward (upward).
* We get `C`.
* We have covered the left side of `C`.
* Now, we cover the right side of `C`. <------------------------------
* We get `F`.
* Let us cover the left side of `F`. <------------------------------
* We get `H`.
* Let us cover the left side of `H`. <------------------------------
* There is no left side of `H`.
* Let us cover the right side of `H`. <------------------------------
* There is no right side of `H`.
* Let us cover the root, `H`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, the path becomes:
* `D -- B -- G -- E -- H` ------------------------------------------------------(5)
* We have just covered the subtree, `H`.
* Now, we move backward (upward).
* We get `F`.
* We have just covered the left side of `F`.
* Now, we cover the right side of `F`. <------------------------------
* We get `I`.
* Let us cover the left side of `I`. <------------------------------
* There is no left side of `I`.
* Let us cover the right side of `I`. <------------------------------
* There is no right side of `I`.
* Let us cover the root, `I`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, the path becomes:
* `D -- B -- G -- E -- H -- I` ------------------------------------------------------(6)
* We have covered the subtree, `I`.
* So, we move backward (upward).
* We get `F`.
* We have covered the left and right sides of `F`.
* So, we cover the root, `F`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, the path becomes:
* `D -- B -- G -- E -- H -- I -- F` ------------------------------------------------------(7)
* We have covered the subtree, `F`.
* So, we move backward (upward).
* We get `C`.
* We have covered the left and right sides of `C`.
* Now, we cover the root, `C`. (It is a root of a subtree that starts with itself.) <------------------------------
* So, the path becomes:
* `D -- B -- G -- E -- H -- I -- F -- C` ------------------------------------------------------(8)
* We have covered the subtree, `C`.
* Now, we move backward (upward).
* We get `A`.
* We have covered the left and right sides of `A`.
* Now, we cover the root, `A`.
* So, the path becomes:
* `D -- B -- G -- E -- H -- I -- F -- C -- A` ------------------------------------------------------(9)
* There is no root (parent) of `A`.
* Hence, the traversal path for the `Post-Order` is:
* `D -- B -- G -- E -- H -- I -- F -- C -- A` ------------------------------------------------------(10)

#### Questions

##### Different depth-first traversal types.

* There are 3 main `depth-first` traversal techniques: `In-Order,` `Pre-Order,` and `Post-Order.`
* `In-Order` follows `Left-Root-Right` order.
* `Pre-Order` follows `Root-Left-Right` order.
* `Post-Order` follows `Left-Right-Root` order.

##### Which tree traversal technique is useful to get a binary tree in a sorted order?

* `In-Order` Traversal for a binary tree gives a sorted order output. [(Reference.)](#the-in-order-traversal-of-a-binary-tree-gives-a-sorted-order-output)

##### Can we use the `In-Order` traversal for a generic tree?

Reference / Resources:

[Coursera's UC San Diego Course: Data Structures: Module 01: Section 03: Trees: Video 02: Timestamp: 03:53](https://coursera.org/share/e9f19723cdd15d5e4f0abe3c8c87fe34)

* The `In-Order` traversal is well-defined (suitable) only for a binary tree.
* Because if a tree node has more than 2 children, what will be the order of processing the node? 
* After the first child, or after the second child?
* This confusion makes the `In-Order` traversal suitable only for a binary tree.
* Because in a binary tree, a node can have a maximum of two children.
* And, it is very clear by the definition of the `In-Order` traversal, that we first process the `Left` children.
* So, the children who are smaller (less) than the node.
* Then, we process the node itself.
* Then, we process the right-side children of the node.
* So, the children who are greater (larger) than the node.
* These structures and rules make the `In-Order` traversal suitable only for a binary tree, not for a generic tree.