try:
    import json
except ImportError:
    import sys
    sys.path.append('simplejson-2.3.3')
    import simplejson as json
    
import urllib



class Tree:

    def __init__(self, url):
        if url != None:
            self.url = url

    def replace_node_names(self, tree, replacements):

        arg_hash = { 'method': 'Tree.replace_node_names',
                     'params': [tree, replacements],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def remove_node_names_and_simplify(self, tree, removal_list):

        arg_hash = { 'method': 'Tree.remove_node_names_and_simplify',
                     'params': [tree, removal_list],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def extract_leaf_node_names(self, tree):

        arg_hash = { 'method': 'Tree.extract_leaf_node_names',
                     'params': [tree],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def extract_node_names(self, tree):

        arg_hash = { 'method': 'Tree.extract_node_names',
                     'params': [tree],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def get_node_count(self, tree):

        arg_hash = { 'method': 'Tree.get_node_count',
                     'params': [tree],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def get_leaf_count(self, tree):

        arg_hash = { 'method': 'Tree.get_leaf_count',
                     'params': [tree],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def get_tree(self, tree_id, options):

        arg_hash = { 'method': 'Tree.get_tree',
                     'params': [tree_id, options],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def get_trees(self, tree_ids, options):

        arg_hash = { 'method': 'Tree.get_trees',
                     'params': [tree_ids, options],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def get_tree_ids_by_feature(self, feature_ids):

        arg_hash = { 'method': 'Tree.get_tree_ids_by_feature',
                     'params': [feature_ids],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def get_tree_ids_by_protein_sequence(self, protein_sequence_ids):

        arg_hash = { 'method': 'Tree.get_tree_ids_by_protein_sequence',
                     'params': [protein_sequence_ids],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def get_alignment_ids_by_feature(self, feature_ids):

        arg_hash = { 'method': 'Tree.get_alignment_ids_by_feature',
                     'params': [feature_ids],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def get_alignment_ids_by_protein_sequence(self, protein_sequence_ids):

        arg_hash = { 'method': 'Tree.get_alignment_ids_by_protein_sequence',
                     'params': [protein_sequence_ids],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def get_kbase_ids_from_alignment_row(self, alignment_id, row_number):

        arg_hash = { 'method': 'Tree.get_kbase_ids_from_alignment_row',
                     'params': [alignment_id, row_number],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None

    def draw_html_tree(self, tree, display_options):

        arg_hash = { 'method': 'Tree.draw_html_tree',
                     'params': [tree, display_options],
                     'version': '1.1'
                     }

        body = json.dumps(arg_hash)
        resp_str = urllib.urlopen(self.url, body).read()
        resp = json.loads(resp_str)

        if 'result' in resp:
            return resp['result'][0]
        else:
            return None




        
