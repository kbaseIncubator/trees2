

function Trees(url) {

    var _url = url;


    this.get_tree = function(tree_id)
    {
	var resp = json_call_ajax_sync("Trees.get_tree", [tree_id]);
//	var resp = json_call_sync("Trees.get_tree", [tree_id]);
        return resp[0];
    }

    this.get_tree_async = function(tree_id, _callback, _error_callback)
    {
	json_call_ajax_async("Trees.get_tree", [tree_id], 1, _callback, _error_callback)
    }

    this.get_trees = function(tree_ids)
    {
	var resp = json_call_ajax_sync("Trees.get_trees", [tree_ids]);
//	var resp = json_call_sync("Trees.get_trees", [tree_ids]);
        return resp[0];
    }

    this.get_trees_async = function(tree_ids, _callback, _error_callback)
    {
	json_call_ajax_async("Trees.get_trees", [tree_ids], 1, _callback, _error_callback)
    }

    this.all_tree_ids = function(isActive)
    {
	var resp = json_call_ajax_sync("Trees.all_tree_ids", [isActive]);
//	var resp = json_call_sync("Trees.all_tree_ids", [isActive]);
        return resp[0];
    }

    this.all_tree_ids_async = function(isActive, _callback, _error_callback)
    {
	json_call_ajax_async("Trees.all_tree_ids", [isActive], 1, _callback, _error_callback)
    }

    this.get_trees_with_entire_seq = function(sequence, beg, end, options)
    {
	var resp = json_call_ajax_sync("Trees.get_trees_with_entire_seq", [sequence, beg, end, options]);
//	var resp = json_call_sync("Trees.get_trees_with_entire_seq", [sequence, beg, end, options]);
        return resp[0];
    }

    this.get_trees_with_entire_seq_async = function(sequence, beg, end, options, _callback, _error_callback)
    {
	json_call_ajax_async("Trees.get_trees_with_entire_seq", [sequence, beg, end, options], 1, _callback, _error_callback)
    }

    this.get_trees_with_overlapping_seq = function(sequence, beg, end, options)
    {
	var resp = json_call_ajax_sync("Trees.get_trees_with_overlapping_seq", [sequence, beg, end, options]);
//	var resp = json_call_sync("Trees.get_trees_with_overlapping_seq", [sequence, beg, end, options]);
        return resp[0];
    }

    this.get_trees_with_overlapping_seq_async = function(sequence, beg, end, options, _callback, _error_callback)
    {
	json_call_ajax_async("Trees.get_trees_with_overlapping_seq", [sequence, beg, end, options], 1, _callback, _error_callback)
    }

    this.get_trees_with_entire_domain = function(domain, options)
    {
	var resp = json_call_ajax_sync("Trees.get_trees_with_entire_domain", [domain, options]);
//	var resp = json_call_sync("Trees.get_trees_with_entire_domain", [domain, options]);
        return resp[0];
    }

    this.get_trees_with_entire_domain_async = function(domain, options, _callback, _error_callback)
    {
	json_call_ajax_async("Trees.get_trees_with_entire_domain", [domain, options], 1, _callback, _error_callback)
    }

    this.get_trees_with_overlapping_domain = function(domain, options)
    {
	var resp = json_call_ajax_sync("Trees.get_trees_with_overlapping_domain", [domain, options]);
//	var resp = json_call_sync("Trees.get_trees_with_overlapping_domain", [domain, options]);
        return resp[0];
    }

    this.get_trees_with_overlapping_domain_async = function(domain, options, _callback, _error_callback)
    {
	json_call_ajax_async("Trees.get_trees_with_overlapping_domain", [domain, options], 1, _callback, _error_callback)
    }

    this.substitute_node_labels_with_kbase_ids = function(trees, options)
    {
	var resp = json_call_ajax_sync("Trees.substitute_node_labels_with_kbase_ids", [trees, options]);
//	var resp = json_call_sync("Trees.substitute_node_labels_with_kbase_ids", [trees, options]);
        return resp[0];
    }

    this.substitute_node_labels_with_kbase_ids_async = function(trees, options, _callback, _error_callback)
    {
	json_call_ajax_async("Trees.substitute_node_labels_with_kbase_ids", [trees, options], 1, _callback, _error_callback)
    }

    this.extract_leaf_node_labels = function(tree)
    {
	var resp = json_call_ajax_sync("Trees.extract_leaf_node_labels", [tree]);
//	var resp = json_call_sync("Trees.extract_leaf_node_labels", [tree]);
        return resp[0];
    }

    this.extract_leaf_node_labels_async = function(tree, _callback, _error_callback)
    {
	json_call_ajax_async("Trees.extract_leaf_node_labels", [tree], 1, _callback, _error_callback)
    }

    function _json_call_prepare(url, method, params, async_flag)
    {
	var rpc = { 'params' : params,
		    'method' : method,
		    'version': "1.1",
	};
	
	var body = JSON.stringify(rpc);
	
	var http = new XMLHttpRequest();
	
	http.open("POST", url, async_flag);
	
	//Send the proper header information along with the request
	http.setRequestHeader("Content-type", "application/json");
	//http.setRequestHeader("Content-length", body.length);
	//http.setRequestHeader("Connection", "close");
	return [http, body];
    }

    /*
     * JSON call using jQuery method.
     */

    function json_call_ajax_sync(method, params)
    {
        var rpc = { 'params' : params,
                    'method' : method,
                    'version': "1.1",
        };
        
        var body = JSON.stringify(rpc);
        var resp_txt;
	var code;
        
        var x = jQuery.ajax({       "async": false,
                                    dataType: "text",
                                    url: _url,
                                    success: function (data, status, xhr) { resp_txt = data; code = xhr.status },
				    error: function(xhr, textStatus, errorThrown) { resp_txt = xhr.responseText, code = xhr.status },
                                    data: body,
                                    processData: false,
                                    type: 'POST',
				    });

        var result;

        if (resp_txt)
        {
	    var resp = JSON.parse(resp_txt);
	    
	    if (code >= 500)
	    {
		throw resp.error;
	    }
	    else
	    {
		return resp.result;
	    }
        }
	else
	{
	    return null;
	}
    }

    function json_call_ajax_async(method, params, num_rets, callback, error_callback)
    {
        var rpc = { 'params' : params,
                    'method' : method,
                    'version': "1.1",
        };
        
        var body = JSON.stringify(rpc);
        var resp_txt;
	var code;
        
        var x = jQuery.ajax({       "async": true,
                                    dataType: "text",
                                    url: _url,
                                    success: function (data, status, xhr)
				{
				    resp = JSON.parse(data);
				    var result = resp["result"];
				    if (num_rets == 1)
				    {
					callback(result[0]);
				    }
				    else
				    {
					callback(result);
				    }
				    
				},
				    error: function(xhr, textStatus, errorThrown)
				{
				    if (xhr.responseText)
				    {
					resp = JSON.parse(xhr.responseText);
					if (error_callback)
					{
					    error_callback(resp.error);
					}
					else
					{
					    throw resp.error;
					}
				    }
				},
                                    data: body,
                                    processData: false,
                                    type: 'POST',
				    });

    }

    function json_call_async(method, params, num_rets, callback)
    {
	var tup = _json_call_prepare(_url, method, params, true);
	var http = tup[0];
	var body = tup[1];
	
	http.onreadystatechange = function() {
	    if (http.readyState == 4 && http.status == 200) {
		var resp_txt = http.responseText;
		var resp = JSON.parse(resp_txt);
		var result = resp["result"];
		if (num_rets == 1)
		{
		    callback(result[0]);
		}
		else
		{
		    callback(result);
		}
	    }
	}
	
	http.send(body);
	
    }
    
    function json_call_sync(method, params)
    {
	var tup = _json_call_prepare(url, method, params, false);
	var http = tup[0];
	var body = tup[1];
	
	http.send(body);
	
	var resp_txt = http.responseText;
	
	var resp = JSON.parse(resp_txt);
	var result = resp["result"];
	    
	return result;
    }
}

