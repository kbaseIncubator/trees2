/**
 * @file kbtree.hh
 * @brief KBase Tree Utility Library
 *
 *
 *
 * @author Michael Sneddon, mwsneddon@lbl.gov
 * @date created Jun 6, 2012, last edited July 2012
 */

#ifndef KBTREE_HH_
#define KBTREE_HH_


#include "tree.hh"
#include <string>
#include <map>

using namespace std;

namespace KBTreeLib {

	class KBTree;

	/*! Encapsulates all information needed about nodes in a KBTree
	 * Lightweight class to store node labels, distance to parent, and associated comments of the node.
	 * You should not have to access this class directly in most cases - instead interact with the
	 * KBTree object methods instead.
	 */
	class KBNode
	{
		public:
			friend class KBTree;

			KBNode();   /*!< Create an empty node with empty, non-null strings for name, comments, labels.  Distance to parent is NAN.  */
			~KBNode();  /*!< Delete an empty node  */

			/*! Returns the full node labeled string as it was ORIGINALLY parsed */
			std::string getOriginalLabel() const { return original_label; };

			/*! Returns the name of the node (from parsing the original label) */
			std::string getName() const { return name; };

			/*! Returns the distance from this node to its parent (from parsing the original label), or NAN if no distance was set */
			double getDistanceToParent() const { return distanceToParent; };

			/*! Returns the bootstrap value of this node (from parsing the original label), or NAN if no distance was set */
			double getBootstrapValue() const { return bootstrapValue; };

			/*! Return a string representation of this Node based on the output style.
			 * @param[in] style Specifies what parts of the node to print to the string.
			 */
			std::string getLabelFromComponents(unsigned int style);

			static const unsigned int NAME_AND_DISTANCE;             /*!< Constant INT to specify output style format for input to getLabelFromComponents(int style)  */
			static const unsigned int NAME_DISTANCE_AND_COMMENTS;    /*!< Constant INT to specify output style format for input to getLabelFromComponents(int style)  */
			static const unsigned int NAME_ONLY;                     /*!< Constant INT to specify output style format for input to getLabelFromComponents(int style)  */
			static const unsigned int DISTANCE_ONLY;                 /*!< Constant INT to specify output style format for input to getLabelFromComponents(int style)  */
			static const unsigned int STRUCTURE_ONLY;                /*!< Constant INT to specify output style format for input to getLabelFromComponents(int style)  */
			static const unsigned int ORIGINAL_LABEL;                /*!< Constant INT to specify output style format for input to getLabelFromComponents(int style)  */

		protected:
			void clear();                      /*!< set all pointers to null, all strings to empty.  This is method is also used for initialization */
			std::string original_label;        /*!< The full, original node label that was parsed, including the name, comments, distance, etc.  */
			std::string name;                  /*!< The parsed name of the node  */
			std::string pre_name_decoration;   /*!< Comments enclosed in [...] before the node name  */
			std::string post_name_decoration;  /*!< Comments enclosed in [...] after the node name  */
			std::string pre_dist_decoration;   /*!< Comments enclosed in [...] before the distance label  */
			std::string post_dist_decoration;  /*!< comments enclosed in [...] after the distance label  */
			double distanceToParent;           /*!< Stores distance to parent if it is defined for this node, if not defined then it is set to NAN  */
			double bootstrapValue;             /*!< Stores bootstrap value (which is parsed ONLY if activated from internal node names), if not defined then it is set to NAN  */
	};



	/**
	 * Class for manipulating trees
	 */
	class KBTree {
		public:
			KBTree(const string &newickString);
			KBTree(const string &newickString, bool verbose);
            KBTree(const std::string &newickString, bool verbose, bool assumeBootstrapNames);
			~KBTree();

			/** allows nodes to count themselves in a tree when the node is created */
			friend class KBNode;

			void initializeFromNewick(const std::string &newick);

			std::string toNewick();
			std::string toNewick(unsigned int style);


			bool writeNewickToFile(const std::string &filename);
			bool writeNewickToFile(const std::string &filename,unsigned int style);

			unsigned int getNodeCount() const { return nodeCount; };
            unsigned int getLeafCount();


			// @todo implement this function
			bool areAllLabelsUnique(string &infoMssg) {return false;};
			// @todo implement this function
			bool validateNewickString(string &infoMssg) {return false;};


			/**
			* returns a string with a list of all the names of the leaves in this tree
			* concatenated as node1;node2;node3; ...
			* * Note that nodes which are not labeled are not returned, so this tree may
			* have more nodes than return values.  Duplicate names are returned as many
			* times as they exist.
			*/
			std::string getAllLeafNames();
			void getAllLeafNames(vector<string> &names);

			/**
			* returns a string with a list of all the names of the nodes in this tree
			* concatenated as node1;node2;node3; ...
			* Note that nodes which are not labeled are not returned, so this tree may
			* have more nodes than return values.  Duplicate names are returned as many
			* times as they exist.
			*/
			std::string getAllNodeNames();
			void getAllNodeNames(vector<string> &names);


			/**
			 * replaces names of nodes in the tree.  This preforms an EXACT string match (ignoring
			 * trailing and leading white space) so it is case sensitive and has no regular expression
			 * characteristics.
			 * @param string originalNodeName[] - array of strings to be found and replaced
			 * @param string replacementNodeName[] - array of strings to be plugged in
			 * @param unsigned int n_names - length of the arrays
			 */
			void replaceNodeNames(std::map<std::string,std::string> &nodeNames);


			/**
			 * Method for removing nodes from a tree and thus simplifying the tree.
			 *  -nodes with a name given as keys in the input map are removed
			 *  -if an internal node has one child and is not named, it is removed
			 *  -if internal nodes are removed, edge distances are summed maintaining root->leaf distances
			 *  -named internal nodes can also removed
			 *  -leaf nodes without a name are removed
			 *  -root node can never be removed, even if they are labeled and in the map
			 * @param std::map<std::string,std::string> &nodeNames - hash listing names of nodes to remove.  Keys are the names, values are not used.
			 */
			void removeNodesByNameAndSimplify(std::map<std::string,std::string> &nodeNames);
			void removeNodesByNameAndSimplify(const std::string &nodeNames);


			void printOutNamesAllPossibleTraversals(ostream &o);

			void printTree();
			/**
			 * Prints the tree to the given output stream in an indented format.  Used primarily for debugging.
			 */
			void printTree(ostream &o);
			static void printTree(ostream &o, const tree<KBNode>& tr, tree<KBNode>::pre_order_iterator it, tree<KBNode>::pre_order_iterator end);

		protected:

			//////////////////// NEWICK PARSING METHODS ///////////////////////////
			/** recursive parsing of a string assuming newick format.  Do not call this method directly outside of KBTree */
			void parseNewick(const std::string &newickString, unsigned int &k, tree<KBNode>::iterator &currentNode);
			bool getNextLabel(const std::string &newickString, unsigned int &k, KBNode &node);
			bool getNextLabelWithoutComments(const std::string &newickString, unsigned int &k, KBNode &node);
			void passLeadingWhiteSpace(const std::string &newickString, unsigned int &k);

			//////////////////// BASIC TREE DATA STRUCTURES ///////////////////////////
			unsigned int nodeCount;
			tree <KBNode> tr;

		private:

			bool verbose;
			bool assumeBootstrapNames;

			/** Internal recursive function called from public toNewick() method.  Never call this method directly **/
			void toNewick(tree<KBNode>::iterator &currentNode, std::string &newickString,unsigned int style);


	};

	/**
		 * @brief allows sending exceptions and checking the stack trace
		 *
		 * ParseException should be thrown whenever a runtime error is encountered
		 * while parsing newick tree or other tree strings.
		 * Whenever this exception type is created, you must give an error message
		 * and the name of the function where the error was generated (include
		 * the class name and / or namespace! as in: KBTree->parse()).  Whenever
		 * you catch such an exception, add the name of your method, and throw it
		 * again.  Then all exceptions will ultimately be handled in
		 * the main function.
		 *
		 * Here is an example of how to use this class to generate an error:
		 *
		 * KBTree::functionCall() {
		 *   if(gotToError)
		 *   	throw(ParseException("Random Error","KBTree::functionCall()"));
		 * }
		 *
		 * Then, if you think you might catch an error, add this code:
		 *
		 * KBTree::funcitonCall2()
		 * {
		 *   try{
		 *     functionCall();
		 *   } catch (ParseException& e) {
		 *     e.addTraceException("KBTree::functionCall2()");
		 *     throw e;
		 *   }
		 * }
		 *
		 *
		 * @author Michael Sneddon
		 * @date Oct 19, 2009 last edited: July 5, 2012
		 */
	class ParseException {
		public:

			/** constructor to use when an error is thrown
			 * @param message the error message, no newline characters please
			 * @param errorLocation the full function name where the error was generated
			 */
			ParseException(string message, string errorLocation) {
				this->message=message;
				this->trace="\t"+errorLocation;
			}

			/** destructor that you shouldn't have to call
			 */
			~ParseException() { };

			/** allows you to add a trace location (function name) when caught
			 * You should use this method to add locations as you throw this
			 * error up the stack trace.  See the class comments on how to
			 * use this method.
			 * @param traceLocation the full function name where this error was caught
			 */
			void addTraceException(string traceLocation) {
				this->trace = "\t" + traceLocation + "\n" + this->trace;
			};

			/** returns the error message in traditional c++ exception style
			 * @return string the error message
			 */
			string what() const { return message; };

			/** get the error message string with the partial stack trace
			 * This method is called in Hive.cpp main and is really the only
			 * place where these errors should ultimately be caught
			 * @return string error message with the stack trace
			 */
			string getFullMessage() const { return message+"\nin:"+trace+"\n"; };

		private:
			string message;
			string trace;
	};



	/**
	 * set of basic utility functions needed for parsing
	 */

	/** in place removal of leading and trailing whitespace **/
	void trim(std::string& str);

	/** given a string, attempts to parse as a double value.  Throws ParseException **/
	double convertToDouble(const std::string& s);

	/** given a double value, returns a string representation **/
	std::string toString(double x);

	/**
	 * this method determines if we have any special characters in the string, and if so, we put quotes around it
	 * and escape out any double quotes, and return the string.  This function is used when returning a newick
	 * string.
	 */
	std::string getQuotedString(const std::string& s);



};


#endif /* KBTREE_HH_ */
