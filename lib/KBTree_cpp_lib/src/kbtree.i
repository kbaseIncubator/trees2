%module KBTreeUtil
%include "std_string.i"

%{
#include "kbtree/kbtree.hh"
%}


namespace KBTreeLib {
    using namespace std;
    class KBTree {
        public:
            KBTree(const std::string &newickString);
            ~KBTree();
            unsigned int getNodeCount() const;
    };
};

