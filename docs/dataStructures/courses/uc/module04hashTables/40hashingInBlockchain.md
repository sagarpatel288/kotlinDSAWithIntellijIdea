# Hashing in Blockchain

* We have many transactions.
* We hash each transaction. We call it leaves.
* Then, we pair and merge hashes of two transactions and create another hash.
* We keep doing it until we cover all the hashes of all the transactions and get a single hash for them.
* We call it a Merkle Root.
* We add this Merkle Root to a blockchain.
* The hash of a previous block is included in the current block.
* Now, we want to check a particular transaction.
* So, we give the transaction ID to a block explorer.
* A block explorer is a set of websites that have already indexed the entire blockchain.
* It gives us the corresponding block.
* Then, we perform the Merkle Proof to check the transaction.