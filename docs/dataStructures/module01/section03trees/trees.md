# Introduction of a Tree

## Table Of Contents

- [What does it represent?](#what-does-it-represent)

## What does it represent?

- [A Structure](#a-structure)
- [Syntax Tree](#a-syntax-tree)
- [Hierarchy](#hierarchy)

### A Structure

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

### A Syntax Tree

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

1. We have this expression: 2 sin(3z - 7)
2. So, there is a final multiplication between 2 and sin(3z - 7)
3. Hence, we can consider multiplication at the root as a final destination (the last operation).
4. So, the root multiplication has two children: 2 and sin(3z - 7).
5. Inside the sin, we have a subtraction between 3z and 7.
6. So, sin has one child "minus" and "minus" has two children: 3z and 7.
7. 3z is a multiplication between 3 and z.
8. If we notice, we keep branching until there are no more children.

### Hierarchy

