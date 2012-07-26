%module KBTreeUtil
%include "std_string.i"
%include "std_map.i"

%{
#include "kbtree/kbtree.hh"
%}


namespace KBTreeLib {

    class KBTree {
        public:
            KBTree(const std::string &newickString);
            KBTree(const std::string &newickString, bool verbose);
            KBTree(const std::string &newickString, bool verbose, bool assumeBootstrapNames);
            ~KBTree();
            
            /*  Returns a newick string representation of the tree */
			std::string toNewick();
			std::string toNewick(unsigned int style);
			
			/*  Writes the newick string representation of the tree to a newick file */
			bool writeNewickToFile(const std::string &filename);
			bool writeNewickToFile(const std::string &filename,unsigned int style);
			
			///////////////////////////////
			//NEEDED FUNCTIONS
			// REPLACE AND SIMPLIFY NODES
			//
			//void removeNodesByNameAndSimplify(std::map<std::string,std::string>& nodeNames);
			void removeNodesByNameAndSimplify(const std::string &nodeNames);
			
			
			/*  Prints the structure of the tree to standard out */
			void printTree();
			
			/* returns the total number of nodes in the tree, including internal nodes */			
            unsigned int getNodeCount() const;
            
            /* returns the total number of leaf nodes (aka tips) in the tree */			
            unsigned int getLeafCount();
    };
    
};

