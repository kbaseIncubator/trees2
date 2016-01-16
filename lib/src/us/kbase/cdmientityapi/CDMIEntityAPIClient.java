package us.kbase.cdmientityapi;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.Tuple3;

/**
 * <p>Original spec-file module name: CDMI_EntityAPI</p>
 * <pre>
 * </pre>
 */
public class CDMIEntityAPIClient {
    private JsonClientCaller caller;
    private static URL DEFAULT_URL = null;
    static {
        try {
            DEFAULT_URL = new URL("https://kbase.us/services/cdmi_api");
        } catch (MalformedURLException mue) {
            throw new RuntimeException("Compile error in client - bad url compiled");
        }
    }

    public CDMIEntityAPIClient() {
       caller = new JsonClientCaller(DEFAULT_URL);
    }

    public CDMIEntityAPIClient(URL url) {
        caller = new JsonClientCaller(url);
    }

    public URL getURL() {
        return caller.getURL();
    }

    public void setConnectionReadTimeOut(Integer milliseconds) {
        this.caller.setConnectionReadTimeOut(milliseconds);
    }

    public void _setFileForNextRpcResponse(File f) {
        caller.setFileForNextRpcResponse(f);
    }

    /**
     * <p>Original spec-file function name: get_all</p>
     * <pre>
     * Wrapper for the GetAll function documented L<here|http://pubseed.theseed.org/sapling/server.cgi?pod=ERDB#GetAll>.
     * Note that the object_names and fields arguments must be strings; array references are not allowed.
     * </pre>
     * @param   objectNames   instance of String
     * @param   filterClause   instance of String
     * @param   parameters   instance of list of String
     * @param   fields   instance of String
     * @param   count   instance of Long
     * @return   parameter "result_set" of list of list of String
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<List<String>> getAll(String objectNames, String filterClause, List<String> parameters, String fields, Long count) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(objectNames);
        args.add(filterClause);
        args.add(parameters);
        args.add(fields);
        args.add(count);
        TypeReference<List<List<List<String>>>> retType = new TypeReference<List<List<List<String>>>>() {};
        List<List<List<String>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_all", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Alignment</p>
     * <pre>
     * An alignment arranges a group of sequences so that they
     * match. Each alignment is associated with a phylogenetic tree that
     * describes how the sequences developed and their evolutionary
     * distance.
     * It has the following fields:
     * =over 4
     * =item n_rows
     * number of rows in the alignment
     * =item n_cols
     * number of columns in the alignment
     * =item status
     * status of the alignment, currently either [i]active[/i], [i]superseded[/i], or [i]bad[/i]
     * =item is_concatenation
     * TRUE if the rows of the alignment map to multiple sequences, FALSE if they map to single sequences
     * =item sequence_type
     * type of sequence being aligned, currently either [i]Protein[/i], [i]DNA[/i], [i]RNA[/i], or [i]Mixed[/i]
     * =item timestamp
     * date and time the alignment was loaded
     * =item method
     * name of the primary software package or script used to construct the alignment
     * =item parameters
     * non-default parameters used as input to the software package or script indicated in the method attribute
     * =item protocol
     * description of the steps taken to construct the alignment, or a reference to an external pipeline
     * =item source_id
     * ID of this alignment in the source database
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlignment> getEntityAlignment(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlignment>>> retType = new TypeReference<List<Map<String,FieldsAlignment>>>() {};
        List<Map<String,FieldsAlignment>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Alignment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Alignment</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlignment> queryEntityAlignment(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlignment>>> retType = new TypeReference<List<Map<String,FieldsAlignment>>>() {};
        List<Map<String,FieldsAlignment>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Alignment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Alignment</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlignment> allEntitiesAlignment(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlignment>>> retType = new TypeReference<List<Map<String,FieldsAlignment>>>() {};
        List<Map<String,FieldsAlignment>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Alignment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_AlignmentAttribute</p>
     * <pre>
     * This entity represents an attribute type that can
     * be assigned to an alignment. The attribute
     * values are stored in the relationships to the target. The
     * key is the attribute name.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlignmentAttribute FieldsAlignmentAttribute} (original type "fields_AlignmentAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlignmentAttribute> getEntityAlignmentAttribute(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlignmentAttribute>>> retType = new TypeReference<List<Map<String,FieldsAlignmentAttribute>>>() {};
        List<Map<String,FieldsAlignmentAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_AlignmentAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_AlignmentAttribute</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlignmentAttribute FieldsAlignmentAttribute} (original type "fields_AlignmentAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlignmentAttribute> queryEntityAlignmentAttribute(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlignmentAttribute>>> retType = new TypeReference<List<Map<String,FieldsAlignmentAttribute>>>() {};
        List<Map<String,FieldsAlignmentAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_AlignmentAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_AlignmentAttribute</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlignmentAttribute FieldsAlignmentAttribute} (original type "fields_AlignmentAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlignmentAttribute> allEntitiesAlignmentAttribute(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlignmentAttribute>>> retType = new TypeReference<List<Map<String,FieldsAlignmentAttribute>>>() {};
        List<Map<String,FieldsAlignmentAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_AlignmentAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_AlignmentRow</p>
     * <pre>
     * This entity represents a single row of an alignment.
     * In general, this corresponds to a sequence, but in a
     * concatenated alignment multiple sequences may be represented
     * here.
     * It has the following fields:
     * =over 4
     * =item row_number
     * 1-based ordinal number of this row in the alignment
     * =item row_id
     * identifier for this row in the FASTA file for the alignment
     * =item row_description
     * description of this row in the FASTA file for the alignment
     * =item n_components
     * number of components that make up this alignment row; for a single-sequence alignment this is always "1"
     * =item beg_pos_aln
     * the 1-based column index in the alignment where this sequence row begins
     * =item end_pos_aln
     * the 1-based column index in the alignment where this sequence row ends
     * =item md5_of_ungapped_sequence
     * the MD5 of this row's sequence after gaps have been removed; for DNA and RNA sequences, the [b]U[/b] codes are also normalized to [b]T[/b] before the MD5 is computed
     * =item sequence
     * sequence for this alignment row (with indels)
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlignmentRow FieldsAlignmentRow} (original type "fields_AlignmentRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlignmentRow> getEntityAlignmentRow(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlignmentRow>>> retType = new TypeReference<List<Map<String,FieldsAlignmentRow>>>() {};
        List<Map<String,FieldsAlignmentRow>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_AlignmentRow", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_AlignmentRow</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlignmentRow FieldsAlignmentRow} (original type "fields_AlignmentRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlignmentRow> queryEntityAlignmentRow(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlignmentRow>>> retType = new TypeReference<List<Map<String,FieldsAlignmentRow>>>() {};
        List<Map<String,FieldsAlignmentRow>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_AlignmentRow", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_AlignmentRow</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlignmentRow FieldsAlignmentRow} (original type "fields_AlignmentRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlignmentRow> allEntitiesAlignmentRow(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlignmentRow>>> retType = new TypeReference<List<Map<String,FieldsAlignmentRow>>>() {};
        List<Map<String,FieldsAlignmentRow>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_AlignmentRow", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_AlleleFrequency</p>
     * <pre>
     * An allele frequency represents a summary of the major and minor allele frequencies for a position on a chromosome.
     * It has the following fields:
     * =over 4
     * =item source_id
     * identifier for this allele in the original (source) database
     * =item position
     * Specific position on the contig where the allele occurs
     * =item minor_AF
     * Minor allele frequency.  Floating point number from 0.0 to 0.5.
     * =item minor_allele
     * Text letter representation of the minor allele. Valid values are A, C, G, and T.
     * =item major_AF
     * Major allele frequency.  Floating point number less than or equal to 1.0.
     * =item major_allele
     * Text letter representation of the major allele. Valid values are A, C, G, and T.
     * =item obs_unit_count
     * Number of observational units used to compute the allele frequencies. Indicates the quality of the analysis.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlleleFrequency FieldsAlleleFrequency} (original type "fields_AlleleFrequency")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlleleFrequency> getEntityAlleleFrequency(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlleleFrequency>>> retType = new TypeReference<List<Map<String,FieldsAlleleFrequency>>>() {};
        List<Map<String,FieldsAlleleFrequency>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_AlleleFrequency", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_AlleleFrequency</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlleleFrequency FieldsAlleleFrequency} (original type "fields_AlleleFrequency")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlleleFrequency> queryEntityAlleleFrequency(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlleleFrequency>>> retType = new TypeReference<List<Map<String,FieldsAlleleFrequency>>>() {};
        List<Map<String,FieldsAlleleFrequency>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_AlleleFrequency", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_AlleleFrequency</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAlleleFrequency FieldsAlleleFrequency} (original type "fields_AlleleFrequency")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAlleleFrequency> allEntitiesAlleleFrequency(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAlleleFrequency>>> retType = new TypeReference<List<Map<String,FieldsAlleleFrequency>>>() {};
        List<Map<String,FieldsAlleleFrequency>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_AlleleFrequency", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Annotation</p>
     * <pre>
     * An annotation is a comment attached to a feature.
     * Annotations are used to track the history of a feature's
     * functional assignments and any related issues. The key is
     * the feature ID followed by a colon and a complemented ten-digit
     * sequence number.
     * It has the following fields:
     * =over 4
     * =item annotator
     * name of the annotator who made the comment
     * =item comment
     * text of the annotation
     * =item annotation_time
     * date and time at which the annotation was made
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAnnotation FieldsAnnotation} (original type "fields_Annotation")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAnnotation> getEntityAnnotation(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAnnotation>>> retType = new TypeReference<List<Map<String,FieldsAnnotation>>>() {};
        List<Map<String,FieldsAnnotation>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Annotation", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Annotation</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAnnotation FieldsAnnotation} (original type "fields_Annotation")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAnnotation> queryEntityAnnotation(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAnnotation>>> retType = new TypeReference<List<Map<String,FieldsAnnotation>>>() {};
        List<Map<String,FieldsAnnotation>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Annotation", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Annotation</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAnnotation FieldsAnnotation} (original type "fields_Annotation")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAnnotation> allEntitiesAnnotation(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAnnotation>>> retType = new TypeReference<List<Map<String,FieldsAnnotation>>>() {};
        List<Map<String,FieldsAnnotation>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Annotation", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Assay</p>
     * <pre>
     * An assay is an experimental design for determining alleles at specific chromosome positions.
     * It has the following fields:
     * =over 4
     * =item source_id
     * identifier for this assay in the original (source) database
     * =item assay_type
     * Text description of the type of assay (e.g., SNP, length, sequence, categorical, array, short read, SSR marker, AFLP marker)
     * =item assay_type_id
     * source ID associated with the assay type (informational)
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssay FieldsAssay} (original type "fields_Assay")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssay> getEntityAssay(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssay>>> retType = new TypeReference<List<Map<String,FieldsAssay>>>() {};
        List<Map<String,FieldsAssay>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Assay", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Assay</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssay FieldsAssay} (original type "fields_Assay")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssay> queryEntityAssay(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssay>>> retType = new TypeReference<List<Map<String,FieldsAssay>>>() {};
        List<Map<String,FieldsAssay>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Assay", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Assay</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssay FieldsAssay} (original type "fields_Assay")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssay> allEntitiesAssay(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssay>>> retType = new TypeReference<List<Map<String,FieldsAssay>>>() {};
        List<Map<String,FieldsAssay>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Assay", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Association</p>
     * <pre>
     * An Association represents a protein complex or a pairwise
     * (binary) physical association between proteins.
     * It has the following fields:
     * =over 4
     * =item name
     * This is the name of the association. 
     * =item description
     * This is a description of this association.  If the protein complex has a name, this should be it. 
     * =item directional
     * True for directional binary associations (e.g., those detected by a pulldown experiment), false for non-directional binary associations and complexes. Bidirectional associations (e.g., associations detected by reciprocal pulldown experiments) should be encoded as 2 separate binary associations. 
     * =item confidence
     * Optional numeric estimate of confidence in the association. Recommended to use a 0-100 scale. 
     * =item url
     * Optional URL for more info about this complex.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssociation FieldsAssociation} (original type "fields_Association")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssociation> getEntityAssociation(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssociation>>> retType = new TypeReference<List<Map<String,FieldsAssociation>>>() {};
        List<Map<String,FieldsAssociation>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Association", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Association</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssociation FieldsAssociation} (original type "fields_Association")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssociation> queryEntityAssociation(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssociation>>> retType = new TypeReference<List<Map<String,FieldsAssociation>>>() {};
        List<Map<String,FieldsAssociation>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Association", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Association</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssociation FieldsAssociation} (original type "fields_Association")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssociation> allEntitiesAssociation(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssociation>>> retType = new TypeReference<List<Map<String,FieldsAssociation>>>() {};
        List<Map<String,FieldsAssociation>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Association", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_AssociationDataset</p>
     * <pre>
     * An Association Dataset is a collection of PPI
     * data imported from a single database or publication.
     * It has the following fields:
     * =over 4
     * =item description
     * This is a description of the dataset.
     * =item data_source
     * Optional external source for this dataset; e.g., another database.
     * =item url
     * Optional URL for more info about this dataset.
     * =item association_type
     * The type of this association.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssociationDataset FieldsAssociationDataset} (original type "fields_AssociationDataset")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssociationDataset> getEntityAssociationDataset(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssociationDataset>>> retType = new TypeReference<List<Map<String,FieldsAssociationDataset>>>() {};
        List<Map<String,FieldsAssociationDataset>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_AssociationDataset", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_AssociationDataset</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssociationDataset FieldsAssociationDataset} (original type "fields_AssociationDataset")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssociationDataset> queryEntityAssociationDataset(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssociationDataset>>> retType = new TypeReference<List<Map<String,FieldsAssociationDataset>>>() {};
        List<Map<String,FieldsAssociationDataset>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_AssociationDataset", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_AssociationDataset</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssociationDataset FieldsAssociationDataset} (original type "fields_AssociationDataset")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssociationDataset> allEntitiesAssociationDataset(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssociationDataset>>> retType = new TypeReference<List<Map<String,FieldsAssociationDataset>>>() {};
        List<Map<String,FieldsAssociationDataset>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_AssociationDataset", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_AssociationDetectionType</p>
     * <pre>
     * This documents methods by which associations are detected
     * or annotated.
     * It has the following fields:
     * =over 4
     * =item description
     * This is a brief description of this detection method. 
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssociationDetectionType FieldsAssociationDetectionType} (original type "fields_AssociationDetectionType")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssociationDetectionType> getEntityAssociationDetectionType(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssociationDetectionType>>> retType = new TypeReference<List<Map<String,FieldsAssociationDetectionType>>>() {};
        List<Map<String,FieldsAssociationDetectionType>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_AssociationDetectionType", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_AssociationDetectionType</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssociationDetectionType FieldsAssociationDetectionType} (original type "fields_AssociationDetectionType")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssociationDetectionType> queryEntityAssociationDetectionType(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssociationDetectionType>>> retType = new TypeReference<List<Map<String,FieldsAssociationDetectionType>>>() {};
        List<Map<String,FieldsAssociationDetectionType>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_AssociationDetectionType", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_AssociationDetectionType</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAssociationDetectionType FieldsAssociationDetectionType} (original type "fields_AssociationDetectionType")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAssociationDetectionType> allEntitiesAssociationDetectionType(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAssociationDetectionType>>> retType = new TypeReference<List<Map<String,FieldsAssociationDetectionType>>>() {};
        List<Map<String,FieldsAssociationDetectionType>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_AssociationDetectionType", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_AtomicRegulon</p>
     * <pre>
     * An atomic regulon is an indivisible group of coregulated
     * features on a single genome. Atomic regulons are constructed so
     * that a given feature can only belong to one. Because of this, the
     * expression levels for atomic regulons represent in some sense the
     * state of a cell.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAtomicRegulon FieldsAtomicRegulon} (original type "fields_AtomicRegulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAtomicRegulon> getEntityAtomicRegulon(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAtomicRegulon>>> retType = new TypeReference<List<Map<String,FieldsAtomicRegulon>>>() {};
        List<Map<String,FieldsAtomicRegulon>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_AtomicRegulon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_AtomicRegulon</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAtomicRegulon FieldsAtomicRegulon} (original type "fields_AtomicRegulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAtomicRegulon> queryEntityAtomicRegulon(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAtomicRegulon>>> retType = new TypeReference<List<Map<String,FieldsAtomicRegulon>>>() {};
        List<Map<String,FieldsAtomicRegulon>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_AtomicRegulon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_AtomicRegulon</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAtomicRegulon FieldsAtomicRegulon} (original type "fields_AtomicRegulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAtomicRegulon> allEntitiesAtomicRegulon(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAtomicRegulon>>> retType = new TypeReference<List<Map<String,FieldsAtomicRegulon>>>() {};
        List<Map<String,FieldsAtomicRegulon>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_AtomicRegulon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Attribute</p>
     * <pre>
     * An attribute describes a category of condition or characteristic for
     * an experiment. The goals of the experiment can be inferred from its values
     * for all the attributes of interest.
     * It has the following fields:
     * =over 4
     * =item description
     * Descriptive text indicating the nature and use of this attribute.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAttribute FieldsAttribute} (original type "fields_Attribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAttribute> getEntityAttribute(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAttribute>>> retType = new TypeReference<List<Map<String,FieldsAttribute>>>() {};
        List<Map<String,FieldsAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Attribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Attribute</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAttribute FieldsAttribute} (original type "fields_Attribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAttribute> queryEntityAttribute(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAttribute>>> retType = new TypeReference<List<Map<String,FieldsAttribute>>>() {};
        List<Map<String,FieldsAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Attribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Attribute</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsAttribute FieldsAttribute} (original type "fields_Attribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsAttribute> allEntitiesAttribute(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsAttribute>>> retType = new TypeReference<List<Map<String,FieldsAttribute>>>() {};
        List<Map<String,FieldsAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Attribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Biomass</p>
     * <pre>
     * A biomass is a collection of compounds in a specific
     * ratio and in specific compartments that are necessary for a
     * cell to function properly. The prediction of biomasses is key
     * to the functioning of the model. Each biomass belongs to
     * a specific model.
     * It has the following fields:
     * =over 4
     * =item mod_date
     * last modification date of the biomass data
     * =item name
     * descriptive name for this biomass
     * =item dna
     * portion of a gram of this biomass (expressed as a fraction of 1.0) that is DNA
     * =item protein
     * portion of a gram of this biomass (expressed as a fraction of 1.0) that is protein
     * =item cell_wall
     * portion of a gram of this biomass (expressed as a fraction of 1.0) that is cell wall
     * =item lipid
     * portion of a gram of this biomass (expressed as a fraction of 1.0) that is lipid but is not part of the cell wall
     * =item cofactor
     * portion of a gram of this biomass (expressed as a fraction of 1.0) that function as cofactors
     * =item energy
     * number of ATP molecules hydrolized per gram of this biomass
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsBiomass FieldsBiomass} (original type "fields_Biomass")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsBiomass> getEntityBiomass(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsBiomass>>> retType = new TypeReference<List<Map<String,FieldsBiomass>>>() {};
        List<Map<String,FieldsBiomass>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Biomass", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Biomass</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsBiomass FieldsBiomass} (original type "fields_Biomass")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsBiomass> queryEntityBiomass(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsBiomass>>> retType = new TypeReference<List<Map<String,FieldsBiomass>>>() {};
        List<Map<String,FieldsBiomass>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Biomass", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Biomass</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsBiomass FieldsBiomass} (original type "fields_Biomass")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsBiomass> allEntitiesBiomass(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsBiomass>>> retType = new TypeReference<List<Map<String,FieldsBiomass>>>() {};
        List<Map<String,FieldsBiomass>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Biomass", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_CodonUsage</p>
     * <pre>
     * This entity contains information about the codon usage
     * frequency in a particular genome with respect to a particular
     * type of analysis (e.g. high-expression genes, modal, mean,
     * etc.).
     * It has the following fields:
     * =over 4
     * =item frequencies
     * A packed-string representation of the codon usage frequencies. These are not global frequencies, but rather frequenicy of use relative to other codons that produce the same amino acid.
     * =item genetic_code
     * Genetic code used for these codons.
     * =item type
     * Type of frequency analysis: average, modal, high-expression, or non-native.
     * =item subtype
     * Specific nature of the codon usage with respect to the given type, generally indicative of how the frequencies were computed.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCodonUsage FieldsCodonUsage} (original type "fields_CodonUsage")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCodonUsage> getEntityCodonUsage(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCodonUsage>>> retType = new TypeReference<List<Map<String,FieldsCodonUsage>>>() {};
        List<Map<String,FieldsCodonUsage>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_CodonUsage", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_CodonUsage</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCodonUsage FieldsCodonUsage} (original type "fields_CodonUsage")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCodonUsage> queryEntityCodonUsage(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCodonUsage>>> retType = new TypeReference<List<Map<String,FieldsCodonUsage>>>() {};
        List<Map<String,FieldsCodonUsage>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_CodonUsage", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_CodonUsage</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCodonUsage FieldsCodonUsage} (original type "fields_CodonUsage")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCodonUsage> allEntitiesCodonUsage(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCodonUsage>>> retType = new TypeReference<List<Map<String,FieldsCodonUsage>>>() {};
        List<Map<String,FieldsCodonUsage>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_CodonUsage", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Complex</p>
     * <pre>
     * A complex is a set of chemical reactions that act in concert to
     * effect a role.
     * It has the following fields:
     * =over 4
     * =item name
     * name of this complex. Not all complexes have names.
     * =item source_id
     * ID of this complex in the source from which it was added.
     * =item mod_date
     * date and time of the last change to this complex's definition
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsComplex FieldsComplex} (original type "fields_Complex")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsComplex> getEntityComplex(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsComplex>>> retType = new TypeReference<List<Map<String,FieldsComplex>>>() {};
        List<Map<String,FieldsComplex>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Complex", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Complex</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsComplex FieldsComplex} (original type "fields_Complex")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsComplex> queryEntityComplex(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsComplex>>> retType = new TypeReference<List<Map<String,FieldsComplex>>>() {};
        List<Map<String,FieldsComplex>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Complex", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Complex</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsComplex FieldsComplex} (original type "fields_Complex")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsComplex> allEntitiesComplex(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsComplex>>> retType = new TypeReference<List<Map<String,FieldsComplex>>>() {};
        List<Map<String,FieldsComplex>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Complex", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Compound</p>
     * <pre>
     * A compound is a chemical that participates in a reaction. Both
     * ligands and reaction components are treated as compounds.
     * It has the following fields:
     * =over 4
     * =item label
     * primary name of the compound, for use in displaying reactions
     * =item abbr
     * shortened abbreviation for the compound name
     * =item source_id
     * common modeling ID of this compound
     * =item ubiquitous
     * TRUE if this compound is found in most reactions, else FALSE
     * =item mod_date
     * date and time of the last modification to the compound definition
     * =item mass
     * pH-neutral atomic mass of the compound
     * =item formula
     * a pH-neutral formula for the compound
     * =item charge
     * computed charge of the compound in a pH-neutral solution
     * =item deltaG
     * the pH 7 reference Gibbs free-energy of formation for this compound as calculated by the group contribution method (units are kcal/mol)
     * =item deltaG_error
     * the uncertainty in the [b]deltaG[/b] value (units are kcal/mol)
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCompound> getEntityCompound(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCompound>>> retType = new TypeReference<List<Map<String,FieldsCompound>>>() {};
        List<Map<String,FieldsCompound>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Compound", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Compound</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCompound> queryEntityCompound(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCompound>>> retType = new TypeReference<List<Map<String,FieldsCompound>>>() {};
        List<Map<String,FieldsCompound>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Compound", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Compound</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCompound> allEntitiesCompound(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCompound>>> retType = new TypeReference<List<Map<String,FieldsCompound>>>() {};
        List<Map<String,FieldsCompound>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Compound", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_CompoundInstance</p>
     * <pre>
     * A Compound Instance represents the occurrence of a particular
     * compound in a location in a model.
     * It has the following fields:
     * =over 4
     * =item charge
     * computed charge based on the location instance pH and similar constraints
     * =item formula
     * computed chemical formula for this compound based on the location instance pH and similar constraints
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCompoundInstance FieldsCompoundInstance} (original type "fields_CompoundInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCompoundInstance> getEntityCompoundInstance(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCompoundInstance>>> retType = new TypeReference<List<Map<String,FieldsCompoundInstance>>>() {};
        List<Map<String,FieldsCompoundInstance>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_CompoundInstance", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_CompoundInstance</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCompoundInstance FieldsCompoundInstance} (original type "fields_CompoundInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCompoundInstance> queryEntityCompoundInstance(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCompoundInstance>>> retType = new TypeReference<List<Map<String,FieldsCompoundInstance>>>() {};
        List<Map<String,FieldsCompoundInstance>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_CompoundInstance", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_CompoundInstance</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCompoundInstance FieldsCompoundInstance} (original type "fields_CompoundInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCompoundInstance> allEntitiesCompoundInstance(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCompoundInstance>>> retType = new TypeReference<List<Map<String,FieldsCompoundInstance>>>() {};
        List<Map<String,FieldsCompoundInstance>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_CompoundInstance", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_ConservedDomainModel</p>
     * <pre>
     * A ConservedDomainModel represents a conserved domain model
     * as found in the NCBI CDD archive.
     * The id of a ConservedDomainModel is the PSSM-Id. 
     * It has the following fields:
     * =over 4
     * =item accession
     * CD accession (starting with 'cd', 'pfam', 'smart', 'COG', 'PRK' or "CHL')
     * =item short_name
     * CD short name
     * =item description
     * CD description
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsConservedDomainModel FieldsConservedDomainModel} (original type "fields_ConservedDomainModel")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsConservedDomainModel> getEntityConservedDomainModel(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsConservedDomainModel>>> retType = new TypeReference<List<Map<String,FieldsConservedDomainModel>>>() {};
        List<Map<String,FieldsConservedDomainModel>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_ConservedDomainModel", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_ConservedDomainModel</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsConservedDomainModel FieldsConservedDomainModel} (original type "fields_ConservedDomainModel")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsConservedDomainModel> queryEntityConservedDomainModel(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsConservedDomainModel>>> retType = new TypeReference<List<Map<String,FieldsConservedDomainModel>>>() {};
        List<Map<String,FieldsConservedDomainModel>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_ConservedDomainModel", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_ConservedDomainModel</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsConservedDomainModel FieldsConservedDomainModel} (original type "fields_ConservedDomainModel")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsConservedDomainModel> allEntitiesConservedDomainModel(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsConservedDomainModel>>> retType = new TypeReference<List<Map<String,FieldsConservedDomainModel>>>() {};
        List<Map<String,FieldsConservedDomainModel>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_ConservedDomainModel", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Contig</p>
     * <pre>
     * A contig is thought of as composing a part of the DNA
     * associated with a specific genome.  It is represented as an ID
     * (including the genome ID) and a ContigSequence. We do not think
     * of strings of DNA from, say, a metgenomic sample as "contigs",
     * since there is no associated genome (these would be considered
     * ContigSequences). This use of the term "ContigSequence", rather
     * than just "DNA sequence", may turn out to be a bad idea.  For now,
     * you should just realize that a Contig has an associated
     * genome, but a ContigSequence does not.
     * It has the following fields:
     * =over 4
     * =item source_id
     * ID of this contig from the core (source) database
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsContig> getEntityContig(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsContig>>> retType = new TypeReference<List<Map<String,FieldsContig>>>() {};
        List<Map<String,FieldsContig>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Contig", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Contig</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsContig> queryEntityContig(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsContig>>> retType = new TypeReference<List<Map<String,FieldsContig>>>() {};
        List<Map<String,FieldsContig>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Contig", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Contig</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsContig> allEntitiesContig(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsContig>>> retType = new TypeReference<List<Map<String,FieldsContig>>>() {};
        List<Map<String,FieldsContig>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Contig", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_ContigChunk</p>
     * <pre>
     * ContigChunks are strings of DNA thought of as being a
     * string in a 4-character alphabet with an associated ID.  We
     * allow a broader alphabet that includes U (for RNA) and
     * the standard ambiguity characters.
     * It has the following fields:
     * =over 4
     * =item sequence
     * base pairs that make up this sequence
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsContigChunk FieldsContigChunk} (original type "fields_ContigChunk")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsContigChunk> getEntityContigChunk(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsContigChunk>>> retType = new TypeReference<List<Map<String,FieldsContigChunk>>>() {};
        List<Map<String,FieldsContigChunk>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_ContigChunk", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_ContigChunk</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsContigChunk FieldsContigChunk} (original type "fields_ContigChunk")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsContigChunk> queryEntityContigChunk(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsContigChunk>>> retType = new TypeReference<List<Map<String,FieldsContigChunk>>>() {};
        List<Map<String,FieldsContigChunk>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_ContigChunk", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_ContigChunk</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsContigChunk FieldsContigChunk} (original type "fields_ContigChunk")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsContigChunk> allEntitiesContigChunk(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsContigChunk>>> retType = new TypeReference<List<Map<String,FieldsContigChunk>>>() {};
        List<Map<String,FieldsContigChunk>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_ContigChunk", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_ContigSequence</p>
     * <pre>
     * ContigSequences are strings of DNA.  Contigs have an
     * associated genome, but ContigSequences do not.  We can think
     * of random samples of DNA as a set of ContigSequences. There
     * are no length constraints imposed on ContigSequences -- they
     * can be either very short or very long.  The basic unit of data
     * that is moved to/from the database is the ContigChunk, from
     * which ContigSequences are formed. The key of a ContigSequence
     * is the sequence's MD5 identifier.
     * It has the following fields:
     * =over 4
     * =item length
     * number of base pairs in the contig
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsContigSequence FieldsContigSequence} (original type "fields_ContigSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsContigSequence> getEntityContigSequence(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsContigSequence>>> retType = new TypeReference<List<Map<String,FieldsContigSequence>>>() {};
        List<Map<String,FieldsContigSequence>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_ContigSequence", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_ContigSequence</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsContigSequence FieldsContigSequence} (original type "fields_ContigSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsContigSequence> queryEntityContigSequence(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsContigSequence>>> retType = new TypeReference<List<Map<String,FieldsContigSequence>>>() {};
        List<Map<String,FieldsContigSequence>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_ContigSequence", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_ContigSequence</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsContigSequence FieldsContigSequence} (original type "fields_ContigSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsContigSequence> allEntitiesContigSequence(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsContigSequence>>> retType = new TypeReference<List<Map<String,FieldsContigSequence>>>() {};
        List<Map<String,FieldsContigSequence>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_ContigSequence", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_CoregulatedSet</p>
     * <pre>
     * We need to represent sets of genes that are coregulated via
     * some regulatory mechanism.  In particular, we wish to represent
     * genes that are coregulated using transcription binding sites and
     * corresponding transcription regulatory proteins. We represent a
     * coregulated set (which may, or may not, be considered a regulon)
     * using CoregulatedSet.
     * It has the following fields:
     * =over 4
     * =item source_id
     * original ID of this coregulated set in the source (core) database
     * =item binding_location
     * binding location for this set's transcription factor; there may be none of these or there may be more than one
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCoregulatedSet FieldsCoregulatedSet} (original type "fields_CoregulatedSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCoregulatedSet> getEntityCoregulatedSet(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCoregulatedSet>>> retType = new TypeReference<List<Map<String,FieldsCoregulatedSet>>>() {};
        List<Map<String,FieldsCoregulatedSet>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_CoregulatedSet", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_CoregulatedSet</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCoregulatedSet FieldsCoregulatedSet} (original type "fields_CoregulatedSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCoregulatedSet> queryEntityCoregulatedSet(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCoregulatedSet>>> retType = new TypeReference<List<Map<String,FieldsCoregulatedSet>>>() {};
        List<Map<String,FieldsCoregulatedSet>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_CoregulatedSet", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_CoregulatedSet</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsCoregulatedSet FieldsCoregulatedSet} (original type "fields_CoregulatedSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsCoregulatedSet> allEntitiesCoregulatedSet(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsCoregulatedSet>>> retType = new TypeReference<List<Map<String,FieldsCoregulatedSet>>>() {};
        List<Map<String,FieldsCoregulatedSet>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_CoregulatedSet", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Diagram</p>
     * <pre>
     * A functional diagram describes a network of chemical
     * reactions, often comprising a single subsystem.
     * It has the following fields:
     * =over 4
     * =item name
     * descriptive name of this diagram
     * =item content
     * content of the diagram, in PNG format
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsDiagram FieldsDiagram} (original type "fields_Diagram")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsDiagram> getEntityDiagram(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsDiagram>>> retType = new TypeReference<List<Map<String,FieldsDiagram>>>() {};
        List<Map<String,FieldsDiagram>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Diagram", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Diagram</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsDiagram FieldsDiagram} (original type "fields_Diagram")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsDiagram> queryEntityDiagram(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsDiagram>>> retType = new TypeReference<List<Map<String,FieldsDiagram>>>() {};
        List<Map<String,FieldsDiagram>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Diagram", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Diagram</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsDiagram FieldsDiagram} (original type "fields_Diagram")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsDiagram> allEntitiesDiagram(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsDiagram>>> retType = new TypeReference<List<Map<String,FieldsDiagram>>>() {};
        List<Map<String,FieldsDiagram>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Diagram", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_EcNumber</p>
     * <pre>
     * EC numbers are assigned by the Enzyme Commission, and consist
     * of four numbers separated by periods, each indicating a successively
     * smaller cateogry of enzymes.
     * It has the following fields:
     * =over 4
     * =item obsolete
     * This boolean indicates when an EC number is obsolete.
     * =item replacedby
     * When an obsolete EC number is replaced with another EC number, this string will hold the name of the replacement EC number.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsEcNumber FieldsEcNumber} (original type "fields_EcNumber")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsEcNumber> getEntityEcNumber(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsEcNumber>>> retType = new TypeReference<List<Map<String,FieldsEcNumber>>>() {};
        List<Map<String,FieldsEcNumber>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_EcNumber", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_EcNumber</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsEcNumber FieldsEcNumber} (original type "fields_EcNumber")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsEcNumber> queryEntityEcNumber(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsEcNumber>>> retType = new TypeReference<List<Map<String,FieldsEcNumber>>>() {};
        List<Map<String,FieldsEcNumber>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_EcNumber", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_EcNumber</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsEcNumber FieldsEcNumber} (original type "fields_EcNumber")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsEcNumber> allEntitiesEcNumber(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsEcNumber>>> retType = new TypeReference<List<Map<String,FieldsEcNumber>>>() {};
        List<Map<String,FieldsEcNumber>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_EcNumber", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Effector</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =item name
     * Name of this effector.
     * =item effector_class
     * The class of this effector.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsEffector FieldsEffector} (original type "fields_Effector")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsEffector> getEntityEffector(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsEffector>>> retType = new TypeReference<List<Map<String,FieldsEffector>>>() {};
        List<Map<String,FieldsEffector>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Effector", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Effector</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsEffector FieldsEffector} (original type "fields_Effector")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsEffector> queryEntityEffector(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsEffector>>> retType = new TypeReference<List<Map<String,FieldsEffector>>>() {};
        List<Map<String,FieldsEffector>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Effector", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Effector</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsEffector FieldsEffector} (original type "fields_Effector")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsEffector> allEntitiesEffector(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsEffector>>> retType = new TypeReference<List<Map<String,FieldsEffector>>>() {};
        List<Map<String,FieldsEffector>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Effector", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Environment</p>
     * <pre>
     * An Environment is a set of conditions for microbial growth,
     * including temperature, aerobicity, media, and supplementary
     * conditions.
     * It has the following fields:
     * =over 4
     * =item temperature
     * The temperature in Kelvin.
     * =item description
     * A description of the environment.
     * =item oxygenConcentration
     * The oxygen concentration in the environment in Molar (mol/L). A value of -1 indicates that there is oxygen in the environment but the concentration is not known, (e.g. an open air shake flask experiment).
     * =item pH
     * The pH of the media used in the environment.
     * =item source_id
     * The ID of the environment used by the data source.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsEnvironment FieldsEnvironment} (original type "fields_Environment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsEnvironment> getEntityEnvironment(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsEnvironment>>> retType = new TypeReference<List<Map<String,FieldsEnvironment>>>() {};
        List<Map<String,FieldsEnvironment>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Environment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Environment</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsEnvironment FieldsEnvironment} (original type "fields_Environment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsEnvironment> queryEntityEnvironment(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsEnvironment>>> retType = new TypeReference<List<Map<String,FieldsEnvironment>>>() {};
        List<Map<String,FieldsEnvironment>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Environment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Environment</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsEnvironment FieldsEnvironment} (original type "fields_Environment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsEnvironment> allEntitiesEnvironment(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsEnvironment>>> retType = new TypeReference<List<Map<String,FieldsEnvironment>>>() {};
        List<Map<String,FieldsEnvironment>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Environment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Experiment</p>
     * <pre>
     * An experiment is a combination of conditions for which gene expression
     * information is desired. The result of the experiment is a set of expression
     * levels for features under the given conditions.
     * It has the following fields:
     * =over 4
     * =item source
     * Publication or lab relevant to this experiment.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperiment> getEntityExperiment(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperiment>>> retType = new TypeReference<List<Map<String,FieldsExperiment>>>() {};
        List<Map<String,FieldsExperiment>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Experiment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Experiment</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperiment> queryEntityExperiment(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperiment>>> retType = new TypeReference<List<Map<String,FieldsExperiment>>>() {};
        List<Map<String,FieldsExperiment>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Experiment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Experiment</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperiment> allEntitiesExperiment(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperiment>>> retType = new TypeReference<List<Map<String,FieldsExperiment>>>() {};
        List<Map<String,FieldsExperiment>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Experiment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_ExperimentMeta</p>
     * <pre>
     * An Experiment consists of (potentially) multiple
     * strains, environments, and measurements on
     * those strains and environments.
     * It has the following fields:
     * =over 4
     * =item title
     * Title of the experiment.
     * =item description
     * Description of the experiment including the experimental plan, general results, and conclusions, if possible.
     * =item source_id
     * The ID of the experiment used by the data source.
     * =item startDate
     * The date this experiment was started.
     * =item comments
     * Any data describing the experiment that is not covered by the description field.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperimentMeta FieldsExperimentMeta} (original type "fields_ExperimentMeta")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperimentMeta> getEntityExperimentMeta(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperimentMeta>>> retType = new TypeReference<List<Map<String,FieldsExperimentMeta>>>() {};
        List<Map<String,FieldsExperimentMeta>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_ExperimentMeta", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_ExperimentMeta</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperimentMeta FieldsExperimentMeta} (original type "fields_ExperimentMeta")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperimentMeta> queryEntityExperimentMeta(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperimentMeta>>> retType = new TypeReference<List<Map<String,FieldsExperimentMeta>>>() {};
        List<Map<String,FieldsExperimentMeta>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_ExperimentMeta", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_ExperimentMeta</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperimentMeta FieldsExperimentMeta} (original type "fields_ExperimentMeta")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperimentMeta> allEntitiesExperimentMeta(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperimentMeta>>> retType = new TypeReference<List<Map<String,FieldsExperimentMeta>>>() {};
        List<Map<String,FieldsExperimentMeta>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_ExperimentMeta", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_ExperimentalUnit</p>
     * <pre>
     * An ExperimentalUnit is a subset of an experiment consisting of
     * a Strain, an Environment, and one or more Measurements on that
     * strain in the specified environment. ExperimentalUnits belong to a
     * single experiment.
     * It has the following fields:
     * =over 4
     * =item source_id
     * The ID of the experimental unit used by the data source.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperimentalUnit> getEntityExperimentalUnit(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperimentalUnit>>> retType = new TypeReference<List<Map<String,FieldsExperimentalUnit>>>() {};
        List<Map<String,FieldsExperimentalUnit>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_ExperimentalUnit", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_ExperimentalUnit</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperimentalUnit> queryEntityExperimentalUnit(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperimentalUnit>>> retType = new TypeReference<List<Map<String,FieldsExperimentalUnit>>>() {};
        List<Map<String,FieldsExperimentalUnit>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_ExperimentalUnit", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_ExperimentalUnit</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperimentalUnit> allEntitiesExperimentalUnit(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperimentalUnit>>> retType = new TypeReference<List<Map<String,FieldsExperimentalUnit>>>() {};
        List<Map<String,FieldsExperimentalUnit>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_ExperimentalUnit", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_ExperimentalUnitGroup</p>
     * <pre>
     * An ExperimentalUnitGroup allows for grouping related experimental units
     * and their measurements - for instance measurements that were in the same plate.
     * It has the following fields:
     * =over 4
     * =item source_id
     * The ID of the experimental unit group used by the data source.
     * =item name
     * The name of this group, if any.
     * =item comments
     * Any comments about this group.
     * =item groupType
     * The type of this grouping, for example '24 well plate', '96 well plate', '384 well plate', 'microarray'.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperimentalUnitGroup FieldsExperimentalUnitGroup} (original type "fields_ExperimentalUnitGroup")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperimentalUnitGroup> getEntityExperimentalUnitGroup(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperimentalUnitGroup>>> retType = new TypeReference<List<Map<String,FieldsExperimentalUnitGroup>>>() {};
        List<Map<String,FieldsExperimentalUnitGroup>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_ExperimentalUnitGroup", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_ExperimentalUnitGroup</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperimentalUnitGroup FieldsExperimentalUnitGroup} (original type "fields_ExperimentalUnitGroup")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperimentalUnitGroup> queryEntityExperimentalUnitGroup(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperimentalUnitGroup>>> retType = new TypeReference<List<Map<String,FieldsExperimentalUnitGroup>>>() {};
        List<Map<String,FieldsExperimentalUnitGroup>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_ExperimentalUnitGroup", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_ExperimentalUnitGroup</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsExperimentalUnitGroup FieldsExperimentalUnitGroup} (original type "fields_ExperimentalUnitGroup")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsExperimentalUnitGroup> allEntitiesExperimentalUnitGroup(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsExperimentalUnitGroup>>> retType = new TypeReference<List<Map<String,FieldsExperimentalUnitGroup>>>() {};
        List<Map<String,FieldsExperimentalUnitGroup>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_ExperimentalUnitGroup", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Family</p>
     * <pre>
     * The Kbase will support the maintenance of protein families
     * (as sets of Features with associated translations).  We are
     * initially only supporting the notion of a family as composed of
     * a set of isofunctional homologs.  That is, the families we
     * initially support should be thought of as containing
     * protein-encoding genes whose associated sequences all implement
     * the same function (we do understand that the notion of "function"
     * is somewhat ambiguous, so let us sweep this under the rug by
     * calling a functional role a "primitive concept").
     * We currently support families in which the members are
     * protein sequences as well. Identical protein sequences
     * as products of translating distinct genes may or may not
     * have identical functions.  This may be justified, since
     * in a very, very, very few cases identical proteins do, in
     * fact, have distinct functions.
     * It has the following fields:
     * =over 4
     * =item type
     * type of protein family (e.g. FIGfam, equivalog)
     * =item release
     * release number / subtype of protein family
     * =item family_function
     * optional free-form description of the family. For function-based families, this would be the functional role for the family members.
     * =item alignment
     * FASTA-formatted alignment of the family's protein sequences
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsFamily> getEntityFamily(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsFamily>>> retType = new TypeReference<List<Map<String,FieldsFamily>>>() {};
        List<Map<String,FieldsFamily>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Family", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Family</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsFamily> queryEntityFamily(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsFamily>>> retType = new TypeReference<List<Map<String,FieldsFamily>>>() {};
        List<Map<String,FieldsFamily>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Family", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Family</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsFamily> allEntitiesFamily(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsFamily>>> retType = new TypeReference<List<Map<String,FieldsFamily>>>() {};
        List<Map<String,FieldsFamily>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Family", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Feature</p>
     * <pre>
     * A feature (sometimes also called a gene) is a part of a
     * genome that is of special interest. Features may be spread across
     * multiple DNA sequences (contigs) of a genome, but never across more
     * than one genome. Each feature in the database has a unique
     * ID that functions as its ID in this table. Normally a Feature is
     * just a single contigous region on a contig. Features have types,
     * and an appropriate choice of available types allows the support
     * of protein-encoding genes, exons, RNA genes, binding sites,
     * pathogenicity islands, or whatever.
     * It has the following fields:
     * =over 4
     * =item feature_type
     * Code indicating the type of this feature. Among the codes currently supported are "peg" for a protein encoding gene, "bs" for a binding site, "opr" for an operon, and so forth.
     * =item source_id
     * ID for this feature in its original source (core) database
     * =item sequence_length
     * Number of base pairs in this feature.
     * =item function
     * Functional assignment for this feature. This will often indicate the feature's functional role or roles, and may also have comments.
     * =item alias
     * alternative identifier for the feature. These are highly unstructured, and frequently non-unique.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsFeature> getEntityFeature(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsFeature>>> retType = new TypeReference<List<Map<String,FieldsFeature>>>() {};
        List<Map<String,FieldsFeature>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Feature", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Feature</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsFeature> queryEntityFeature(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsFeature>>> retType = new TypeReference<List<Map<String,FieldsFeature>>>() {};
        List<Map<String,FieldsFeature>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Feature", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Feature</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsFeature> allEntitiesFeature(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsFeature>>> retType = new TypeReference<List<Map<String,FieldsFeature>>>() {};
        List<Map<String,FieldsFeature>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Feature", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Genome</p>
     * <pre>
     * The Kbase houses a large and growing set of genomes.  We
     * often have multiple genomes that have identical DNA.  These usually
     * have distinct gene calls and annotations, but not always.  We
     * consider the Kbase to be a framework for managing hundreds of
     * thousands of genomes and offering the tools needed to
     * support compartive analysis on large sets of genomes,
     * some of which are virtually identical.
     * It has the following fields:
     * =over 4
     * =item pegs
     * Number of protein encoding genes for this genome.
     * =item rnas
     * Number of RNA features found for this organism.
     * =item scientific_name
     * Full genus/species/strain name of the genome sequence.
     * =item complete
     * TRUE if the genome sequence is complete, else FALSE
     * =item prokaryotic
     * TRUE if this is a prokaryotic genome sequence, else FALSE
     * =item dna_size
     * Number of base pairs in the genome sequence.
     * =item contigs
     * Number of contigs for this genome sequence.
     * =item domain
     * Domain for this organism (Archaea, Bacteria, Eukaryota, Virus, Plasmid, or Environmental Sample).
     * =item genetic_code
     * Genetic code number used for protein translation on most of this genome sequence's contigs.
     * =item gc_content
     * Percent GC content present in the genome sequence's DNA.
     * =item phenotype
     * zero or more strings describing phenotypic information about this genome sequence
     * =item md5
     * MD5 identifier describing the genome's DNA sequence
     * =item source_id
     * identifier assigned to this genome by the original source
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsGenome> getEntityGenome(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsGenome>>> retType = new TypeReference<List<Map<String,FieldsGenome>>>() {};
        List<Map<String,FieldsGenome>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Genome", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Genome</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsGenome> queryEntityGenome(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsGenome>>> retType = new TypeReference<List<Map<String,FieldsGenome>>>() {};
        List<Map<String,FieldsGenome>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Genome", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Genome</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsGenome> allEntitiesGenome(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsGenome>>> retType = new TypeReference<List<Map<String,FieldsGenome>>>() {};
        List<Map<String,FieldsGenome>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Genome", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Locality</p>
     * <pre>
     * A locality is a geographic location.
     * It has the following fields:
     * =over 4
     * =item source_name
     * Name or description of the location used as a collection site.
     * =item city
     * City of the collecting site.
     * =item state
     * State or province of the collecting site.
     * =item country
     * Country of the collecting site.
     * =item origcty
     * 3-letter ISO 3166-1 extended country code for the country of origin.
     * =item elevation
     * Elevation of the collecting site, expressed in meters above sea level.  Negative values are allowed.
     * =item latitude
     * Latitude of the collecting site, recorded as a decimal number.  North latitudes are positive values and south latitudes are negative numbers.
     * =item longitude
     * Longitude of the collecting site, recorded as a decimal number.  West longitudes are positive values and east longitudes are negative numbers.
     * =item lo_accession
     * gazeteer ontology term ID
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocality FieldsLocality} (original type "fields_Locality")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocality> getEntityLocality(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocality>>> retType = new TypeReference<List<Map<String,FieldsLocality>>>() {};
        List<Map<String,FieldsLocality>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Locality", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Locality</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocality FieldsLocality} (original type "fields_Locality")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocality> queryEntityLocality(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocality>>> retType = new TypeReference<List<Map<String,FieldsLocality>>>() {};
        List<Map<String,FieldsLocality>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Locality", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Locality</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocality FieldsLocality} (original type "fields_Locality")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocality> allEntitiesLocality(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocality>>> retType = new TypeReference<List<Map<String,FieldsLocality>>>() {};
        List<Map<String,FieldsLocality>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Locality", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_LocalizedCompound</p>
     * <pre>
     * This entity represents a compound occurring in a
     * specific location. A reaction always involves localized
     * compounds. If a reaction occurs entirely in a single
     * location, it will frequently only be represented by the
     * cytoplasmic versions of the compounds; however, a transport
     * always uses specifically located compounds.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocalizedCompound FieldsLocalizedCompound} (original type "fields_LocalizedCompound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocalizedCompound> getEntityLocalizedCompound(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocalizedCompound>>> retType = new TypeReference<List<Map<String,FieldsLocalizedCompound>>>() {};
        List<Map<String,FieldsLocalizedCompound>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_LocalizedCompound", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_LocalizedCompound</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocalizedCompound FieldsLocalizedCompound} (original type "fields_LocalizedCompound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocalizedCompound> queryEntityLocalizedCompound(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocalizedCompound>>> retType = new TypeReference<List<Map<String,FieldsLocalizedCompound>>>() {};
        List<Map<String,FieldsLocalizedCompound>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_LocalizedCompound", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_LocalizedCompound</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocalizedCompound FieldsLocalizedCompound} (original type "fields_LocalizedCompound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocalizedCompound> allEntitiesLocalizedCompound(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocalizedCompound>>> retType = new TypeReference<List<Map<String,FieldsLocalizedCompound>>>() {};
        List<Map<String,FieldsLocalizedCompound>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_LocalizedCompound", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Location</p>
     * <pre>
     * A location is a region of the cell where reaction compounds
     * originate from or are transported to (e.g. cell wall, extracellular,
     * cytoplasm).
     * It has the following fields:
     * =over 4
     * =item mod_date
     * date and time of the last modification to the compartment's definition
     * =item name
     * common name for the location
     * =item source_id
     * ID from the source of this location
     * =item abbr
     * an abbreviation (usually a single letter) for the location.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocation FieldsLocation} (original type "fields_Location")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocation> getEntityLocation(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocation>>> retType = new TypeReference<List<Map<String,FieldsLocation>>>() {};
        List<Map<String,FieldsLocation>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Location", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Location</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocation FieldsLocation} (original type "fields_Location")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocation> queryEntityLocation(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocation>>> retType = new TypeReference<List<Map<String,FieldsLocation>>>() {};
        List<Map<String,FieldsLocation>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Location", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Location</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocation FieldsLocation} (original type "fields_Location")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocation> allEntitiesLocation(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocation>>> retType = new TypeReference<List<Map<String,FieldsLocation>>>() {};
        List<Map<String,FieldsLocation>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Location", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_LocationInstance</p>
     * <pre>
     * The Location Instance represents a region of a cell
     * (e.g. cell wall, cytoplasm) as it appears in a specific
     * model.
     * It has the following fields:
     * =over 4
     * =item index
     * number used to distinguish between different instances of the same type of location in a single model. Within a model, any two instances of the same location must have difference compartment index values.
     * =item label
     * description used to differentiate between instances of the same location in a single model
     * =item pH
     * pH of the cell region, which is used to determine compound charge and pH gradient across cell membranes
     * =item potential
     * electrochemical potential of the cell region, which is used to determine the electrochemical gradient across cell membranes
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocationInstance FieldsLocationInstance} (original type "fields_LocationInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocationInstance> getEntityLocationInstance(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocationInstance>>> retType = new TypeReference<List<Map<String,FieldsLocationInstance>>>() {};
        List<Map<String,FieldsLocationInstance>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_LocationInstance", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_LocationInstance</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocationInstance FieldsLocationInstance} (original type "fields_LocationInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocationInstance> queryEntityLocationInstance(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocationInstance>>> retType = new TypeReference<List<Map<String,FieldsLocationInstance>>>() {};
        List<Map<String,FieldsLocationInstance>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_LocationInstance", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_LocationInstance</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsLocationInstance FieldsLocationInstance} (original type "fields_LocationInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsLocationInstance> allEntitiesLocationInstance(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsLocationInstance>>> retType = new TypeReference<List<Map<String,FieldsLocationInstance>>>() {};
        List<Map<String,FieldsLocationInstance>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_LocationInstance", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Measurement</p>
     * <pre>
     * A Measurement is a value generated by performing a protocol to
     * evaluate a value on an ExperimentalUnit - e.g. a strain in an
     * environment.
     * It has the following fields:
     * =over 4
     * =item source_id
     * The ID of the measurement used by the data source.
     * =item value
     * The value of the measurement.
     * =item mean
     * The mean of multiple replicates if they are included in the measurement.
     * =item median
     * The median of multiple replicates if they are included in the measurement.
     * =item stddev
     * The standard deviation of multiple replicates if they are included in the measurement.
     * =item N
     * The number of replicates if they are included in the measurement.
     * =item p_value
     * The p-value of multiple replicates if they are included in the measurement. The exact meaning of the p-value is specified in the MeasurementDescription object for this measurement.
     * =item Z_score
     * The Z-score of multiple replicates if they are included in the measurement. The exact meaning of the Z-score is specified in the MeasurementDescription object for this measurement.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsMeasurement> getEntityMeasurement(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsMeasurement>>> retType = new TypeReference<List<Map<String,FieldsMeasurement>>>() {};
        List<Map<String,FieldsMeasurement>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Measurement", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Measurement</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsMeasurement> queryEntityMeasurement(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsMeasurement>>> retType = new TypeReference<List<Map<String,FieldsMeasurement>>>() {};
        List<Map<String,FieldsMeasurement>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Measurement", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Measurement</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsMeasurement> allEntitiesMeasurement(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsMeasurement>>> retType = new TypeReference<List<Map<String,FieldsMeasurement>>>() {};
        List<Map<String,FieldsMeasurement>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Measurement", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_MeasurementDescription</p>
     * <pre>
     * A MeasurementDescription provides information about a
     * measurement value.
     * It has the following fields:
     * =over 4
     * =item name
     * The name of the measurement.
     * =item description
     * The description of the measurement, how it is measured, and what the measurement statistics mean.
     * =item unitOfMeasure
     * The units of the measurement.
     * =item category
     * The category the measurement fits into, for example phenotype, experimental input, environment.
     * =item source_id
     * The ID of the measurement description used by the data source.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsMeasurementDescription FieldsMeasurementDescription} (original type "fields_MeasurementDescription")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsMeasurementDescription> getEntityMeasurementDescription(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsMeasurementDescription>>> retType = new TypeReference<List<Map<String,FieldsMeasurementDescription>>>() {};
        List<Map<String,FieldsMeasurementDescription>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_MeasurementDescription", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_MeasurementDescription</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsMeasurementDescription FieldsMeasurementDescription} (original type "fields_MeasurementDescription")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsMeasurementDescription> queryEntityMeasurementDescription(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsMeasurementDescription>>> retType = new TypeReference<List<Map<String,FieldsMeasurementDescription>>>() {};
        List<Map<String,FieldsMeasurementDescription>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_MeasurementDescription", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_MeasurementDescription</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsMeasurementDescription FieldsMeasurementDescription} (original type "fields_MeasurementDescription")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsMeasurementDescription> allEntitiesMeasurementDescription(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsMeasurementDescription>>> retType = new TypeReference<List<Map<String,FieldsMeasurementDescription>>>() {};
        List<Map<String,FieldsMeasurementDescription>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_MeasurementDescription", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Media</p>
     * <pre>
     * A media describes the chemical content of the solution in which cells
     * are grown in an experiment or for the purposes of a model. The key is the
     * common media name. The nature of the media is described by its relationship
     * to its constituent compounds.
     * It has the following fields:
     * =over 4
     * =item mod_date
     * date and time of the last modification to the media's definition
     * =item name
     * descriptive name of the media
     * =item is_minimal
     * TRUE if this is a minimal media, else FALSE
     * =item source_id
     * The ID of the media used by the data source.
     * =item type
     * The general category of the media.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsMedia FieldsMedia} (original type "fields_Media")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsMedia> getEntityMedia(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsMedia>>> retType = new TypeReference<List<Map<String,FieldsMedia>>>() {};
        List<Map<String,FieldsMedia>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Media", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Media</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsMedia FieldsMedia} (original type "fields_Media")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsMedia> queryEntityMedia(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsMedia>>> retType = new TypeReference<List<Map<String,FieldsMedia>>>() {};
        List<Map<String,FieldsMedia>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Media", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Media</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsMedia FieldsMedia} (original type "fields_Media")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsMedia> allEntitiesMedia(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsMedia>>> retType = new TypeReference<List<Map<String,FieldsMedia>>>() {};
        List<Map<String,FieldsMedia>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Media", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Model</p>
     * <pre>
     * A model specifies a relationship between sets of features and
     * reactions in a cell. It is used to simulate cell growth and gene
     * knockouts to validate annotations.
     * It has the following fields:
     * =over 4
     * =item mod_date
     * date and time of the last change to the model data
     * =item name
     * descriptive name of the model
     * =item version
     * revision number of the model
     * =item type
     * string indicating where the model came from (e.g. single genome, multiple genome, or community model)
     * =item status
     * indicator of whether the model is stable, under construction, or under reconstruction
     * =item reaction_count
     * number of reactions in the model
     * =item compound_count
     * number of compounds in the model
     * =item annotation_count
     * number of features associated with one or more reactions in the model
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsModel FieldsModel} (original type "fields_Model")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsModel> getEntityModel(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsModel>>> retType = new TypeReference<List<Map<String,FieldsModel>>>() {};
        List<Map<String,FieldsModel>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Model", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Model</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsModel FieldsModel} (original type "fields_Model")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsModel> queryEntityModel(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsModel>>> retType = new TypeReference<List<Map<String,FieldsModel>>>() {};
        List<Map<String,FieldsModel>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Model", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Model</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsModel FieldsModel} (original type "fields_Model")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsModel> allEntitiesModel(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsModel>>> retType = new TypeReference<List<Map<String,FieldsModel>>>() {};
        List<Map<String,FieldsModel>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Model", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_OTU</p>
     * <pre>
     * An OTU (Organism Taxonomic Unit) is a named group of related
     * genomes.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsOTU FieldsOTU} (original type "fields_OTU")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsOTU> getEntityOTU(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsOTU>>> retType = new TypeReference<List<Map<String,FieldsOTU>>>() {};
        List<Map<String,FieldsOTU>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_OTU", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_OTU</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsOTU FieldsOTU} (original type "fields_OTU")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsOTU> queryEntityOTU(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsOTU>>> retType = new TypeReference<List<Map<String,FieldsOTU>>>() {};
        List<Map<String,FieldsOTU>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_OTU", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_OTU</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsOTU FieldsOTU} (original type "fields_OTU")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsOTU> allEntitiesOTU(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsOTU>>> retType = new TypeReference<List<Map<String,FieldsOTU>>>() {};
        List<Map<String,FieldsOTU>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_OTU", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_ObservationalUnit</p>
     * <pre>
     * An ObservationalUnit is an individual plant that 1) is part of an experiment or study, 2) has measured traits, and 3) is assayed for the purpose of determining alleles.  
     * It has the following fields:
     * =over 4
     * =item source_name
     * Name/ID by which the observational unit may be known by the originator and is used in queries.
     * =item source_name2
     * Secondary name/ID by which the observational unit may be known and is queried.
     * =item plant_id
     * ID of the plant that was tested to produce this observational unit. Observational units with the same plant ID are different assays of a single physical organism.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsObservationalUnit> getEntityObservationalUnit(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsObservationalUnit>>> retType = new TypeReference<List<Map<String,FieldsObservationalUnit>>>() {};
        List<Map<String,FieldsObservationalUnit>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_ObservationalUnit", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_ObservationalUnit</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsObservationalUnit> queryEntityObservationalUnit(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsObservationalUnit>>> retType = new TypeReference<List<Map<String,FieldsObservationalUnit>>>() {};
        List<Map<String,FieldsObservationalUnit>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_ObservationalUnit", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_ObservationalUnit</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsObservationalUnit> allEntitiesObservationalUnit(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsObservationalUnit>>> retType = new TypeReference<List<Map<String,FieldsObservationalUnit>>>() {};
        List<Map<String,FieldsObservationalUnit>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_ObservationalUnit", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Ontology</p>
     * <pre>
     * -- Environmental Ontology. (ENVO Terms) http://environmentontology.org/  
     * -- Plant Ontology (PO Terms). http://www.plantontology.org/   
     * -- Plant Environmental Ontology (EO Terms). http://www.gramene.org/plant_ontology/index.html#eo
     * -- ENVO : http://envo.googlecode.com/svn/trunk/src/envo/envo-basic.obo
     * -- PO : http://palea.cgrb.oregonstate.edu/viewsvn/Poc/tags/live/plant_ontology.obo?view=co
     * -- EO : http://obo.cvs.sourceforge.net/viewvc/obo/obo/ontology/phenotype/environment/environment_ontology.obo
     * It has the following fields:
     * =over 4
     * =item id
     * Ontologgy ID.
     * =item name
     * Type of the ontology.
     * =item definition
     * Definition of the ontology
     * =item ontologySource
     * Enumerated value (ENVO, EO, PO).
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsOntology FieldsOntology} (original type "fields_Ontology")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsOntology> getEntityOntology(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsOntology>>> retType = new TypeReference<List<Map<String,FieldsOntology>>>() {};
        List<Map<String,FieldsOntology>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Ontology", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Ontology</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsOntology FieldsOntology} (original type "fields_Ontology")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsOntology> queryEntityOntology(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsOntology>>> retType = new TypeReference<List<Map<String,FieldsOntology>>>() {};
        List<Map<String,FieldsOntology>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Ontology", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Ontology</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsOntology FieldsOntology} (original type "fields_Ontology")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsOntology> allEntitiesOntology(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsOntology>>> retType = new TypeReference<List<Map<String,FieldsOntology>>>() {};
        List<Map<String,FieldsOntology>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Ontology", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Operon</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsOperon FieldsOperon} (original type "fields_Operon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsOperon> getEntityOperon(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsOperon>>> retType = new TypeReference<List<Map<String,FieldsOperon>>>() {};
        List<Map<String,FieldsOperon>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Operon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Operon</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsOperon FieldsOperon} (original type "fields_Operon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsOperon> queryEntityOperon(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsOperon>>> retType = new TypeReference<List<Map<String,FieldsOperon>>>() {};
        List<Map<String,FieldsOperon>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Operon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Operon</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsOperon FieldsOperon} (original type "fields_Operon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsOperon> allEntitiesOperon(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsOperon>>> retType = new TypeReference<List<Map<String,FieldsOperon>>>() {};
        List<Map<String,FieldsOperon>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Operon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_PairSet</p>
     * <pre>
     * A PairSet is a precompute set of pairs or genes.  Each
     * pair occurs close to one another of the chromosome.  We believe
     * that all of the first members of the pairs correspond to one another
     * (are quite similar), as do all of the second members of the pairs.
     * These pairs (from prokaryotic genomes) offer one of the most
     * powerful clues relating to uncharacterized genes/peroteins.
     * It has the following fields:
     * =over 4
     * =item score
     * Score for this evidence set. The score indicates the number of significantly different genomes represented by the pairings.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPairSet FieldsPairSet} (original type "fields_PairSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPairSet> getEntityPairSet(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPairSet>>> retType = new TypeReference<List<Map<String,FieldsPairSet>>>() {};
        List<Map<String,FieldsPairSet>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_PairSet", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_PairSet</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPairSet FieldsPairSet} (original type "fields_PairSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPairSet> queryEntityPairSet(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPairSet>>> retType = new TypeReference<List<Map<String,FieldsPairSet>>>() {};
        List<Map<String,FieldsPairSet>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_PairSet", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_PairSet</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPairSet FieldsPairSet} (original type "fields_PairSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPairSet> allEntitiesPairSet(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPairSet>>> retType = new TypeReference<List<Map<String,FieldsPairSet>>>() {};
        List<Map<String,FieldsPairSet>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_PairSet", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Pairing</p>
     * <pre>
     * A pairing indicates that two features are found
     * close together in a genome. Not all possible pairings are stored in
     * the database; only those that are considered for some reason to be
     * significant for annotation purposes.The key of the pairing is the
     * concatenation of the feature IDs in alphabetical order with an
     * intervening colon.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPairing FieldsPairing} (original type "fields_Pairing")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPairing> getEntityPairing(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPairing>>> retType = new TypeReference<List<Map<String,FieldsPairing>>>() {};
        List<Map<String,FieldsPairing>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Pairing", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Pairing</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPairing FieldsPairing} (original type "fields_Pairing")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPairing> queryEntityPairing(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPairing>>> retType = new TypeReference<List<Map<String,FieldsPairing>>>() {};
        List<Map<String,FieldsPairing>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Pairing", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Pairing</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPairing FieldsPairing} (original type "fields_Pairing")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPairing> allEntitiesPairing(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPairing>>> retType = new TypeReference<List<Map<String,FieldsPairing>>>() {};
        List<Map<String,FieldsPairing>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Pairing", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Parameter</p>
     * <pre>
     * A parameter is the name of some quantity that has a value.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsParameter FieldsParameter} (original type "fields_Parameter")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsParameter> getEntityParameter(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsParameter>>> retType = new TypeReference<List<Map<String,FieldsParameter>>>() {};
        List<Map<String,FieldsParameter>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Parameter", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Parameter</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsParameter FieldsParameter} (original type "fields_Parameter")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsParameter> queryEntityParameter(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsParameter>>> retType = new TypeReference<List<Map<String,FieldsParameter>>>() {};
        List<Map<String,FieldsParameter>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Parameter", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Parameter</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsParameter FieldsParameter} (original type "fields_Parameter")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsParameter> allEntitiesParameter(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsParameter>>> retType = new TypeReference<List<Map<String,FieldsParameter>>>() {};
        List<Map<String,FieldsParameter>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Parameter", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Person</p>
     * <pre>
     * A person represents a human affiliated in some way with Kbase.
     * It has the following fields:
     * =over 4
     * =item firstName
     * The given name of the person.
     * =item lastName
     * The surname of the person.
     * =item contactEmail
     * Email address of the person.
     * =item institution
     * The institution where the person works.
     * =item source_id
     * The ID of the person used by the data source.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPerson FieldsPerson} (original type "fields_Person")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPerson> getEntityPerson(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPerson>>> retType = new TypeReference<List<Map<String,FieldsPerson>>>() {};
        List<Map<String,FieldsPerson>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Person", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Person</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPerson FieldsPerson} (original type "fields_Person")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPerson> queryEntityPerson(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPerson>>> retType = new TypeReference<List<Map<String,FieldsPerson>>>() {};
        List<Map<String,FieldsPerson>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Person", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Person</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPerson FieldsPerson} (original type "fields_Person")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPerson> allEntitiesPerson(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPerson>>> retType = new TypeReference<List<Map<String,FieldsPerson>>>() {};
        List<Map<String,FieldsPerson>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Person", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Platform</p>
     * <pre>
     * Platform that the expression sample/experiment was run on.
     * It has the following fields:
     * =over 4
     * =item title
     * free text title of the comparison
     * =item externalSourceId
     * The externalSourceId gives users potentially an easy way to find the data of interest (ex:GPL514). This will keep them from having to do problematic likes on the source-id field.
     * =item technology
     * Ideally enumerated values, but may have to make this free text (spotted DNA/cDNA, spotted oligonucleotide, in situ oligonucleotide, antibody, tissue, SARST, RT-PCR, or MPSS).
     * =item type
     * Enumerated Microarray, RNA-Seq, qPCR
     * =item source_id
     * The ID used as the data source.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPlatform FieldsPlatform} (original type "fields_Platform")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPlatform> getEntityPlatform(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPlatform>>> retType = new TypeReference<List<Map<String,FieldsPlatform>>>() {};
        List<Map<String,FieldsPlatform>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Platform", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Platform</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPlatform FieldsPlatform} (original type "fields_Platform")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPlatform> queryEntityPlatform(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPlatform>>> retType = new TypeReference<List<Map<String,FieldsPlatform>>>() {};
        List<Map<String,FieldsPlatform>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Platform", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Platform</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPlatform FieldsPlatform} (original type "fields_Platform")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPlatform> allEntitiesPlatform(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPlatform>>> retType = new TypeReference<List<Map<String,FieldsPlatform>>>() {};
        List<Map<String,FieldsPlatform>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Platform", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_ProbeSet</p>
     * <pre>
     * A probe set is a device containing multiple probe sequences for use
     * in gene expression experiments.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsProbeSet FieldsProbeSet} (original type "fields_ProbeSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsProbeSet> getEntityProbeSet(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsProbeSet>>> retType = new TypeReference<List<Map<String,FieldsProbeSet>>>() {};
        List<Map<String,FieldsProbeSet>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_ProbeSet", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_ProbeSet</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsProbeSet FieldsProbeSet} (original type "fields_ProbeSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsProbeSet> queryEntityProbeSet(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsProbeSet>>> retType = new TypeReference<List<Map<String,FieldsProbeSet>>>() {};
        List<Map<String,FieldsProbeSet>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_ProbeSet", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_ProbeSet</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsProbeSet FieldsProbeSet} (original type "fields_ProbeSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsProbeSet> allEntitiesProbeSet(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsProbeSet>>> retType = new TypeReference<List<Map<String,FieldsProbeSet>>>() {};
        List<Map<String,FieldsProbeSet>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_ProbeSet", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_ProteinSequence</p>
     * <pre>
     * We use the concept of ProteinSequence as an amino acid
     * string with an associated MD5 value.  It is easy to access the
     * set of Features that relate to a ProteinSequence.  While function
     * is still associated with Features (and may be for some time),
     * publications are associated with ProteinSequences (and the inferred
     * impact on Features is through the relationship connecting
     * ProteinSequences to Features).
     * It has the following fields:
     * =over 4
     * =item sequence
     * The sequence contains the letters corresponding to the protein's amino acids.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsProteinSequence> getEntityProteinSequence(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsProteinSequence>>> retType = new TypeReference<List<Map<String,FieldsProteinSequence>>>() {};
        List<Map<String,FieldsProteinSequence>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_ProteinSequence", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_ProteinSequence</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsProteinSequence> queryEntityProteinSequence(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsProteinSequence>>> retType = new TypeReference<List<Map<String,FieldsProteinSequence>>>() {};
        List<Map<String,FieldsProteinSequence>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_ProteinSequence", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_ProteinSequence</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsProteinSequence> allEntitiesProteinSequence(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsProteinSequence>>> retType = new TypeReference<List<Map<String,FieldsProteinSequence>>>() {};
        List<Map<String,FieldsProteinSequence>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_ProteinSequence", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Protocol</p>
     * <pre>
     * A Protocol is a step by step set of instructions for
     * performing a part of an experiment.
     * It has the following fields:
     * =over 4
     * =item name
     * The name of the protocol.
     * =item description
     * The step by step instructions for performing the experiment, including measurement details, materials, and equipment. A researcher should be able to reproduce the experimental results with this information.
     * =item source_id
     * The ID of the protocol used by the data source.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsProtocol FieldsProtocol} (original type "fields_Protocol")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsProtocol> getEntityProtocol(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsProtocol>>> retType = new TypeReference<List<Map<String,FieldsProtocol>>>() {};
        List<Map<String,FieldsProtocol>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Protocol", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Protocol</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsProtocol FieldsProtocol} (original type "fields_Protocol")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsProtocol> queryEntityProtocol(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsProtocol>>> retType = new TypeReference<List<Map<String,FieldsProtocol>>>() {};
        List<Map<String,FieldsProtocol>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Protocol", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Protocol</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsProtocol FieldsProtocol} (original type "fields_Protocol")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsProtocol> allEntitiesProtocol(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsProtocol>>> retType = new TypeReference<List<Map<String,FieldsProtocol>>>() {};
        List<Map<String,FieldsProtocol>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Protocol", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Publication</p>
     * <pre>
     * Experimenters attach publications to experiments and
     * protocols. Annotators attach publications to ProteinSequences.
     * The attached publications give an ID (usually a
     * DOI or Pubmed ID),  a URL to the paper (when we have it), and a title
     * (when we have it). Pubmed IDs are given unmodified. DOI IDs
     * are prefixed with [b]doi:[/b], e.g. [i]doi:1002385[/i].
     * It has the following fields:
     * =over 4
     * =item title
     * title of the article, or (unknown) if the title is not known
     * =item link
     * URL of the article, DOI preferred
     * =item pubdate
     * publication date of the article
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPublication> getEntityPublication(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPublication>>> retType = new TypeReference<List<Map<String,FieldsPublication>>>() {};
        List<Map<String,FieldsPublication>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Publication", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Publication</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPublication> queryEntityPublication(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPublication>>> retType = new TypeReference<List<Map<String,FieldsPublication>>>() {};
        List<Map<String,FieldsPublication>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Publication", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Publication</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsPublication> allEntitiesPublication(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsPublication>>> retType = new TypeReference<List<Map<String,FieldsPublication>>>() {};
        List<Map<String,FieldsPublication>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Publication", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Reaction</p>
     * <pre>
     * A reaction is a chemical process that converts one set of
     * compounds (substrate) to another set (products).
     * It has the following fields:
     * =over 4
     * =item mod_date
     * date and time of the last modification to this reaction's definition
     * =item name
     * descriptive name of this reaction
     * =item source_id
     * ID of this reaction in the resource from which it was added
     * =item abbr
     * abbreviated name of this reaction
     * =item direction
     * direction of this reaction (> for forward-only, < for backward-only, = for bidirectional)
     * =item deltaG
     * Gibbs free-energy change for the reaction calculated using the group contribution method (units are kcal/mol)
     * =item deltaG_error
     * uncertainty in the [b]deltaG[/b] value (units are kcal/mol)
     * =item thermodynamic_reversibility
     * computed reversibility of this reaction in a pH-neutral environment
     * =item default_protons
     * number of protons absorbed by this reaction in a pH-neutral environment
     * =item status
     * string indicating additional information about this reaction, generally indicating whether the reaction is balanced and/or lumped
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsReaction> getEntityReaction(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsReaction>>> retType = new TypeReference<List<Map<String,FieldsReaction>>>() {};
        List<Map<String,FieldsReaction>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Reaction", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Reaction</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsReaction> queryEntityReaction(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsReaction>>> retType = new TypeReference<List<Map<String,FieldsReaction>>>() {};
        List<Map<String,FieldsReaction>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Reaction", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Reaction</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsReaction> allEntitiesReaction(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsReaction>>> retType = new TypeReference<List<Map<String,FieldsReaction>>>() {};
        List<Map<String,FieldsReaction>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Reaction", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_ReactionInstance</p>
     * <pre>
     * A reaction instance describes the specific implementation of
     * a reaction in a model.
     * It has the following fields:
     * =over 4
     * =item direction
     * reaction directionality (> for forward, < for backward, = for bidirectional) with respect to this model
     * =item protons
     * number of protons produced by this reaction when proceeding in the forward direction. If this is a transport reaction, these protons end up in the reaction instance's main location. If the number is negative, then the protons are consumed by the reaction rather than being produced.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsReactionInstance FieldsReactionInstance} (original type "fields_ReactionInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsReactionInstance> getEntityReactionInstance(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsReactionInstance>>> retType = new TypeReference<List<Map<String,FieldsReactionInstance>>>() {};
        List<Map<String,FieldsReactionInstance>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_ReactionInstance", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_ReactionInstance</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsReactionInstance FieldsReactionInstance} (original type "fields_ReactionInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsReactionInstance> queryEntityReactionInstance(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsReactionInstance>>> retType = new TypeReference<List<Map<String,FieldsReactionInstance>>>() {};
        List<Map<String,FieldsReactionInstance>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_ReactionInstance", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_ReactionInstance</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsReactionInstance FieldsReactionInstance} (original type "fields_ReactionInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsReactionInstance> allEntitiesReactionInstance(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsReactionInstance>>> retType = new TypeReference<List<Map<String,FieldsReactionInstance>>>() {};
        List<Map<String,FieldsReactionInstance>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_ReactionInstance", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Regulator</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =item name
     * A human-readable name for this Regulator. 
     * =item rfam_id
     * If this regulator is an RNA regulator, the rfam-id field will contain the RFAM identifier corresponding to it. 
     * =item tf_family
     * If this regulator is a transcription factor, then the tf-family field will contain the name of the transcription factor family. 
     * =item type
     * Type of the regulator; currently either RNA or TF. 
     * =item taxonomy
     * Type of the regulator; currently either RNA or TF. 
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulator FieldsRegulator} (original type "fields_Regulator")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulator> getEntityRegulator(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulator>>> retType = new TypeReference<List<Map<String,FieldsRegulator>>>() {};
        List<Map<String,FieldsRegulator>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Regulator", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Regulator</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulator FieldsRegulator} (original type "fields_Regulator")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulator> queryEntityRegulator(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulator>>> retType = new TypeReference<List<Map<String,FieldsRegulator>>>() {};
        List<Map<String,FieldsRegulator>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Regulator", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Regulator</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulator FieldsRegulator} (original type "fields_Regulator")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulator> allEntitiesRegulator(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulator>>> retType = new TypeReference<List<Map<String,FieldsRegulator>>>() {};
        List<Map<String,FieldsRegulator>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Regulator", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Regulog</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =item description
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulog FieldsRegulog} (original type "fields_Regulog")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulog> getEntityRegulog(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulog>>> retType = new TypeReference<List<Map<String,FieldsRegulog>>>() {};
        List<Map<String,FieldsRegulog>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Regulog", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Regulog</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulog FieldsRegulog} (original type "fields_Regulog")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulog> queryEntityRegulog(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulog>>> retType = new TypeReference<List<Map<String,FieldsRegulog>>>() {};
        List<Map<String,FieldsRegulog>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Regulog", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Regulog</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulog FieldsRegulog} (original type "fields_Regulog")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulog> allEntitiesRegulog(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulog>>> retType = new TypeReference<List<Map<String,FieldsRegulog>>>() {};
        List<Map<String,FieldsRegulog>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Regulog", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_RegulogCollection</p>
     * <pre>
     * A RegulogCollection describes a set of regulogs that are being
     * curated on well-defined set of genomes.
     * It has the following fields:
     * =over 4
     * =item name
     * The name of this regulog collection. 
     * =item description
     * A brief description of this regulog collection. 
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulogCollection FieldsRegulogCollection} (original type "fields_RegulogCollection")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulogCollection> getEntityRegulogCollection(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulogCollection>>> retType = new TypeReference<List<Map<String,FieldsRegulogCollection>>>() {};
        List<Map<String,FieldsRegulogCollection>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_RegulogCollection", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_RegulogCollection</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulogCollection FieldsRegulogCollection} (original type "fields_RegulogCollection")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulogCollection> queryEntityRegulogCollection(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulogCollection>>> retType = new TypeReference<List<Map<String,FieldsRegulogCollection>>>() {};
        List<Map<String,FieldsRegulogCollection>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_RegulogCollection", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_RegulogCollection</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulogCollection FieldsRegulogCollection} (original type "fields_RegulogCollection")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulogCollection> allEntitiesRegulogCollection(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulogCollection>>> retType = new TypeReference<List<Map<String,FieldsRegulogCollection>>>() {};
        List<Map<String,FieldsRegulogCollection>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_RegulogCollection", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Regulome</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =item description
     * A short description for this regulome. 
     * =item creation_date
     * Creation date for this regulome.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulome FieldsRegulome} (original type "fields_Regulome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulome> getEntityRegulome(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulome>>> retType = new TypeReference<List<Map<String,FieldsRegulome>>>() {};
        List<Map<String,FieldsRegulome>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Regulome", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Regulome</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulome FieldsRegulome} (original type "fields_Regulome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulome> queryEntityRegulome(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulome>>> retType = new TypeReference<List<Map<String,FieldsRegulome>>>() {};
        List<Map<String,FieldsRegulome>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Regulome", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Regulome</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulome FieldsRegulome} (original type "fields_Regulome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulome> allEntitiesRegulome(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulome>>> retType = new TypeReference<List<Map<String,FieldsRegulome>>>() {};
        List<Map<String,FieldsRegulome>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Regulome", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Regulon</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =item description
     * A short description for this regulon. 
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulon> getEntityRegulon(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulon>>> retType = new TypeReference<List<Map<String,FieldsRegulon>>>() {};
        List<Map<String,FieldsRegulon>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Regulon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Regulon</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulon> queryEntityRegulon(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulon>>> retType = new TypeReference<List<Map<String,FieldsRegulon>>>() {};
        List<Map<String,FieldsRegulon>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Regulon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Regulon</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRegulon> allEntitiesRegulon(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRegulon>>> retType = new TypeReference<List<Map<String,FieldsRegulon>>>() {};
        List<Map<String,FieldsRegulon>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Regulon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_ReplicateGroup</p>
     * <pre>
     * Keeps track of Replicate Groups of Expression Samples.  Has only an ID.  Relationship is the important part.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsReplicateGroup FieldsReplicateGroup} (original type "fields_ReplicateGroup")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsReplicateGroup> getEntityReplicateGroup(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsReplicateGroup>>> retType = new TypeReference<List<Map<String,FieldsReplicateGroup>>>() {};
        List<Map<String,FieldsReplicateGroup>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_ReplicateGroup", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_ReplicateGroup</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsReplicateGroup FieldsReplicateGroup} (original type "fields_ReplicateGroup")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsReplicateGroup> queryEntityReplicateGroup(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsReplicateGroup>>> retType = new TypeReference<List<Map<String,FieldsReplicateGroup>>>() {};
        List<Map<String,FieldsReplicateGroup>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_ReplicateGroup", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_ReplicateGroup</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsReplicateGroup FieldsReplicateGroup} (original type "fields_ReplicateGroup")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsReplicateGroup> allEntitiesReplicateGroup(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsReplicateGroup>>> retType = new TypeReference<List<Map<String,FieldsReplicateGroup>>>() {};
        List<Map<String,FieldsReplicateGroup>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_ReplicateGroup", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Role</p>
     * <pre>
     * A role describes a biological function that may be fulfilled
     * by a feature. One of the main goals of the database is to assign
     * features to roles. Most roles are effected by the construction of
     * proteins. Some, however, deal with functional regulation and message
     * transmission.
     * It has the following fields:
     * =over 4
     * =item hypothetical
     * TRUE if a role is hypothetical, else FALSE
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRole> getEntityRole(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRole>>> retType = new TypeReference<List<Map<String,FieldsRole>>>() {};
        List<Map<String,FieldsRole>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Role", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Role</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRole> queryEntityRole(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRole>>> retType = new TypeReference<List<Map<String,FieldsRole>>>() {};
        List<Map<String,FieldsRole>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Role", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Role</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsRole> allEntitiesRole(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsRole>>> retType = new TypeReference<List<Map<String,FieldsRole>>>() {};
        List<Map<String,FieldsRole>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Role", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_SSCell</p>
     * <pre>
     * An SSCell (SpreadSheet Cell) represents a role as it occurs
     * in a subsystem spreadsheet row. The key is a colon-delimited triple
     * containing an MD5 hash of the subsystem ID followed by a genome ID
     * (with optional region string) and a role abbreviation.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSSCell FieldsSSCell} (original type "fields_SSCell")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSSCell> getEntitySSCell(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSSCell>>> retType = new TypeReference<List<Map<String,FieldsSSCell>>>() {};
        List<Map<String,FieldsSSCell>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_SSCell", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_SSCell</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSSCell FieldsSSCell} (original type "fields_SSCell")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSSCell> queryEntitySSCell(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSSCell>>> retType = new TypeReference<List<Map<String,FieldsSSCell>>>() {};
        List<Map<String,FieldsSSCell>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_SSCell", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_SSCell</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSSCell FieldsSSCell} (original type "fields_SSCell")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSSCell> allEntitiesSSCell(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSSCell>>> retType = new TypeReference<List<Map<String,FieldsSSCell>>>() {};
        List<Map<String,FieldsSSCell>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_SSCell", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_SSRow</p>
     * <pre>
     * An SSRow (that is, a row in a subsystem spreadsheet)
     * represents a collection of functional roles present in the
     * Features of a single Genome.  The roles are part of a designated
     * subsystem, and the features associated with each role are included
     * in the row. That is, a row amounts to an instance of a subsystem as
     * it exists in a specific, designated genome.
     * It has the following fields:
     * =over 4
     * =item curated
     * This flag is TRUE if the assignment of the molecular machine has been curated, and FALSE if it was made by an automated program.
     * =item region
     * Region in the genome for which the row is relevant. Normally, this is an empty string, indicating that the machine covers the whole genome. If a subsystem has multiple rows for a genome, this contains a location string describing the region occupied by this particular row.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSSRow FieldsSSRow} (original type "fields_SSRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSSRow> getEntitySSRow(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSSRow>>> retType = new TypeReference<List<Map<String,FieldsSSRow>>>() {};
        List<Map<String,FieldsSSRow>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_SSRow", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_SSRow</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSSRow FieldsSSRow} (original type "fields_SSRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSSRow> queryEntitySSRow(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSSRow>>> retType = new TypeReference<List<Map<String,FieldsSSRow>>>() {};
        List<Map<String,FieldsSSRow>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_SSRow", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_SSRow</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSSRow FieldsSSRow} (original type "fields_SSRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSSRow> allEntitiesSSRow(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSSRow>>> retType = new TypeReference<List<Map<String,FieldsSSRow>>>() {};
        List<Map<String,FieldsSSRow>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_SSRow", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Sample</p>
     * <pre>
     * A sample is an experiment.  
     * In intensity experiment situations the sample will map 1 to 1 to the GSM.  
     * In this case there will be corresponding log2level data stored in the Measurement table.
     * It has the following fields:
     * =over 4
     * =item title
     * free text title of the sample
     * =item dataSource
     * The Data Source will be a way to identify where the data came from.  Examples might be : GEO, SEED Expression Pipeline, Enigma, M3D
     * =item externalSourceId
     * The externalSourceId gives users potentially an easy way to find the data of interest (ex:GSM9514). This will keep them from having to do problematic likes on the source-id field.
     * =item description
     * Free-text descibing the experiment.
     * =item molecule
     * Enumerated field (total RNA, polyA RNA, cytoplasmic RNA, nuclear RNA, genomic DNA).
     * =item type
     * Enumerated Microarray, RNA-Seq, qPCR
     * =item kbaseSubmissionDate
     * date of submission to Kbase
     * =item externalSourceDate
     * date that may exist in the external source metadata (could be to GEO, M3D etc...)
     * =item custom
     * A flag to keep track if this series was generated by custom operations (averaging or comparison)
     * =item originalLog2Median
     * The Original Median of the sample in log2space.  If null means the original median was not able to be determined.
     * =item source_id
     * The ID of the environment used by the data source.
     * =item dataQualityLevel
     * The quality of the data.  Lower the number the better.  Details need to be worked out.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSample> getEntitySample(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSample>>> retType = new TypeReference<List<Map<String,FieldsSample>>>() {};
        List<Map<String,FieldsSample>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Sample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Sample</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSample> queryEntitySample(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSample>>> retType = new TypeReference<List<Map<String,FieldsSample>>>() {};
        List<Map<String,FieldsSample>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Sample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Sample</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSample> allEntitiesSample(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSample>>> retType = new TypeReference<List<Map<String,FieldsSample>>>() {};
        List<Map<String,FieldsSample>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Sample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_SampleAnnotation</p>
     * <pre>
     * Keeps track of ontology annotation date (and person if not automated).
     * It has the following fields:
     * =over 4
     * =item annotationDate
     * date of annotation
     * =item source_id
     * The ID of the environment used by the data source.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSampleAnnotation FieldsSampleAnnotation} (original type "fields_SampleAnnotation")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSampleAnnotation> getEntitySampleAnnotation(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSampleAnnotation>>> retType = new TypeReference<List<Map<String,FieldsSampleAnnotation>>>() {};
        List<Map<String,FieldsSampleAnnotation>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_SampleAnnotation", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_SampleAnnotation</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSampleAnnotation FieldsSampleAnnotation} (original type "fields_SampleAnnotation")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSampleAnnotation> queryEntitySampleAnnotation(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSampleAnnotation>>> retType = new TypeReference<List<Map<String,FieldsSampleAnnotation>>>() {};
        List<Map<String,FieldsSampleAnnotation>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_SampleAnnotation", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_SampleAnnotation</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSampleAnnotation FieldsSampleAnnotation} (original type "fields_SampleAnnotation")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSampleAnnotation> allEntitiesSampleAnnotation(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSampleAnnotation>>> retType = new TypeReference<List<Map<String,FieldsSampleAnnotation>>>() {};
        List<Map<String,FieldsSampleAnnotation>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_SampleAnnotation", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Scenario</p>
     * <pre>
     * A scenario is a partial instance of a subsystem with a
     * defined set of reactions. Each scenario converts input compounds to
     * output compounds using reactions. The scenario may use all of the
     * reactions controlled by a subsystem or only some, and may also
     * incorporate additional reactions. Because scenario names are not
     * unique, the actual scenario ID is a number.
     * It has the following fields:
     * =over 4
     * =item common_name
     * Common name of the scenario. The name, rather than the ID number, is usually displayed everywhere.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsScenario FieldsScenario} (original type "fields_Scenario")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsScenario> getEntityScenario(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsScenario>>> retType = new TypeReference<List<Map<String,FieldsScenario>>>() {};
        List<Map<String,FieldsScenario>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Scenario", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Scenario</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsScenario FieldsScenario} (original type "fields_Scenario")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsScenario> queryEntityScenario(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsScenario>>> retType = new TypeReference<List<Map<String,FieldsScenario>>>() {};
        List<Map<String,FieldsScenario>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Scenario", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Scenario</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsScenario FieldsScenario} (original type "fields_Scenario")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsScenario> allEntitiesScenario(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsScenario>>> retType = new TypeReference<List<Map<String,FieldsScenario>>>() {};
        List<Map<String,FieldsScenario>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Scenario", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Series</p>
     * <pre>
     * A series refers to a group of samples for expression data.
     * It has the following fields:
     * =over 4
     * =item title
     * free text title of the series
     * =item summary
     * free text summary of the series
     * =item design
     * free text design of the series
     * =item externalSourceId
     * The externalSourceId gives users potentially an easy way to find the data of interest (ex:GSE2365). This will keep them from having to do problematic likes on the source-id field.
     * =item kbaseSubmissionDate
     * date of submission (to Kbase)
     * =item externalSourceDate
     * date that may exist in the external source metadata (could be to GEO, M3D etc...)
     * =item source_id
     * The ID of the environment used by the data source.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSeries FieldsSeries} (original type "fields_Series")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSeries> getEntitySeries(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSeries>>> retType = new TypeReference<List<Map<String,FieldsSeries>>>() {};
        List<Map<String,FieldsSeries>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Series", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Series</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSeries FieldsSeries} (original type "fields_Series")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSeries> queryEntitySeries(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSeries>>> retType = new TypeReference<List<Map<String,FieldsSeries>>>() {};
        List<Map<String,FieldsSeries>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Series", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Series</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSeries FieldsSeries} (original type "fields_Series")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSeries> allEntitiesSeries(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSeries>>> retType = new TypeReference<List<Map<String,FieldsSeries>>>() {};
        List<Map<String,FieldsSeries>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Series", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Source</p>
     * <pre>
     * A source is a user or organization that is permitted to
     * assign its own identifiers or to submit bioinformatic objects
     * to the database.
     * It has the following fields:
     * =over 4
     * =item name
     * The user-readable name for this source.
     * =item url
     * The URL to a site with information about this source.
     * =item description
     * A short textual description of this source.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSource> getEntitySource(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSource>>> retType = new TypeReference<List<Map<String,FieldsSource>>>() {};
        List<Map<String,FieldsSource>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Source", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Source</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSource> queryEntitySource(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSource>>> retType = new TypeReference<List<Map<String,FieldsSource>>>() {};
        List<Map<String,FieldsSource>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Source", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Source</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSource> allEntitiesSource(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSource>>> retType = new TypeReference<List<Map<String,FieldsSource>>>() {};
        List<Map<String,FieldsSource>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Source", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Strain</p>
     * <pre>
     * This entity represents an organism derived from a genome or
     * another organism with one or more modifications to the organism's
     * genome.
     * It has the following fields:
     * =over 4
     * =item name
     * The common or laboratory name of the strain, e.g. DH5a or JMP1004.
     * =item description
     * A description of the strain, e.g. knockout/modification methods, resulting phenotypes, etc.
     * =item source_id
     * The ID of the strain used by the data source.
     * =item aggregateData
     * Denotes whether this entity represents a physical strain (False) or aggregate data calculated from one or more strains (True).
     * =item wildtype
     * Denotes this strain is presumably identical to the parent genome.
     * =item referenceStrain
     * Denotes whether this strain is a reference strain; e.g. it is identical to the genome it's related to (True) or not (False). In contrast to wildtype, a referenceStrain is abstract and does not physically exist and is used for data that refers to a genome but not a particular strain. There should only exist one reference strain per genome and all reference strains are wildtype. 
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsStrain> getEntityStrain(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsStrain>>> retType = new TypeReference<List<Map<String,FieldsStrain>>>() {};
        List<Map<String,FieldsStrain>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Strain", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Strain</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsStrain> queryEntityStrain(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsStrain>>> retType = new TypeReference<List<Map<String,FieldsStrain>>>() {};
        List<Map<String,FieldsStrain>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Strain", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Strain</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsStrain> allEntitiesStrain(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsStrain>>> retType = new TypeReference<List<Map<String,FieldsStrain>>>() {};
        List<Map<String,FieldsStrain>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Strain", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_StudyExperiment</p>
     * <pre>
     * An Experiment is a collection of observational units with one originator that are part of a specific study.  An experiment may be conducted at more than one location and in more than one season or year.
     * It has the following fields:
     * =over 4
     * =item source_name
     * Name/ID by which the experiment is known at the source.  
     * =item design
     * Design of the experiment including the numbers and types of observational units, traits, replicates, sampling plan, and analysis that are planned.
     * =item originator
     * Name of the individual or program that are the originators of the experiment.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsStudyExperiment FieldsStudyExperiment} (original type "fields_StudyExperiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsStudyExperiment> getEntityStudyExperiment(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsStudyExperiment>>> retType = new TypeReference<List<Map<String,FieldsStudyExperiment>>>() {};
        List<Map<String,FieldsStudyExperiment>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_StudyExperiment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_StudyExperiment</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsStudyExperiment FieldsStudyExperiment} (original type "fields_StudyExperiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsStudyExperiment> queryEntityStudyExperiment(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsStudyExperiment>>> retType = new TypeReference<List<Map<String,FieldsStudyExperiment>>>() {};
        List<Map<String,FieldsStudyExperiment>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_StudyExperiment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_StudyExperiment</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsStudyExperiment FieldsStudyExperiment} (original type "fields_StudyExperiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsStudyExperiment> allEntitiesStudyExperiment(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsStudyExperiment>>> retType = new TypeReference<List<Map<String,FieldsStudyExperiment>>>() {};
        List<Map<String,FieldsStudyExperiment>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_StudyExperiment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Subsystem</p>
     * <pre>
     * A subsystem is a set of functional roles that have been annotated simultaneously (e.g.,
     * the roles present in a specific pathway), with an associated subsystem spreadsheet
     * which encodes the fids in each genome that implement the functional roles in the
     * subsystem.
     * It has the following fields:
     * =over 4
     * =item version
     * version number for the subsystem. This value is incremented each time the subsystem is backed up.
     * =item curator
     * name of the person currently in charge of the subsystem
     * =item notes
     * descriptive notes about the subsystem
     * =item description
     * description of the subsystem's function in the cell
     * =item usable
     * TRUE if this is a usable subsystem, else FALSE. An unusable subsystem is one that is experimental or is of such low quality that it can negatively affect analysis.
     * =item private
     * TRUE if this is a private subsystem, else FALSE. A private subsystem has valid data, but is not considered ready for general distribution.
     * =item cluster_based
     * TRUE if this is a clustering-based subsystem, else FALSE. A clustering-based subsystem is one in which there is functional-coupling evidence that genes belong together, but we do not yet know what they do.
     * =item experimental
     * TRUE if this is an experimental subsystem, else FALSE. An experimental subsystem is designed for investigation and is not yet ready to be used in comparative analysis and annotation.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSubsystem> getEntitySubsystem(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSubsystem>>> retType = new TypeReference<List<Map<String,FieldsSubsystem>>>() {};
        List<Map<String,FieldsSubsystem>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Subsystem", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Subsystem</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSubsystem> queryEntitySubsystem(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSubsystem>>> retType = new TypeReference<List<Map<String,FieldsSubsystem>>>() {};
        List<Map<String,FieldsSubsystem>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Subsystem", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Subsystem</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSubsystem> allEntitiesSubsystem(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSubsystem>>> retType = new TypeReference<List<Map<String,FieldsSubsystem>>>() {};
        List<Map<String,FieldsSubsystem>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Subsystem", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_SubsystemClass</p>
     * <pre>
     * Subsystem classes impose a hierarchical organization on the
     * subsystems.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSubsystemClass FieldsSubsystemClass} (original type "fields_SubsystemClass")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSubsystemClass> getEntitySubsystemClass(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSubsystemClass>>> retType = new TypeReference<List<Map<String,FieldsSubsystemClass>>>() {};
        List<Map<String,FieldsSubsystemClass>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_SubsystemClass", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_SubsystemClass</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSubsystemClass FieldsSubsystemClass} (original type "fields_SubsystemClass")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSubsystemClass> queryEntitySubsystemClass(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSubsystemClass>>> retType = new TypeReference<List<Map<String,FieldsSubsystemClass>>>() {};
        List<Map<String,FieldsSubsystemClass>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_SubsystemClass", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_SubsystemClass</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsSubsystemClass FieldsSubsystemClass} (original type "fields_SubsystemClass")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsSubsystemClass> allEntitiesSubsystemClass(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsSubsystemClass>>> retType = new TypeReference<List<Map<String,FieldsSubsystemClass>>>() {};
        List<Map<String,FieldsSubsystemClass>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_SubsystemClass", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_TaxonomicGrouping</p>
     * <pre>
     * We associate with most genomes a "taxonomy" based on
     * the NCBI taxonomy. This includes, for each genome, a list of
     * ever larger taxonomic groups. The groups are stored as
     * instances of this entity, and chained together by the
     * IsGroupFor relationship.
     * It has the following fields:
     * =over 4
     * =item domain
     * TRUE if this is a domain grouping, else FALSE.
     * =item hidden
     * TRUE if this is a hidden grouping, else FALSE. Hidden groupings are not typically shown in a lineage list.
     * =item scientific_name
     * Primary scientific name for this grouping. This is the name used when displaying a taxonomy.
     * =item alias
     * Alternate name for this grouping. A grouping may have many alternate names. The scientific name should also be in this list.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTaxonomicGrouping FieldsTaxonomicGrouping} (original type "fields_TaxonomicGrouping")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTaxonomicGrouping> getEntityTaxonomicGrouping(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTaxonomicGrouping>>> retType = new TypeReference<List<Map<String,FieldsTaxonomicGrouping>>>() {};
        List<Map<String,FieldsTaxonomicGrouping>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_TaxonomicGrouping", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_TaxonomicGrouping</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTaxonomicGrouping FieldsTaxonomicGrouping} (original type "fields_TaxonomicGrouping")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTaxonomicGrouping> queryEntityTaxonomicGrouping(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTaxonomicGrouping>>> retType = new TypeReference<List<Map<String,FieldsTaxonomicGrouping>>>() {};
        List<Map<String,FieldsTaxonomicGrouping>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_TaxonomicGrouping", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_TaxonomicGrouping</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTaxonomicGrouping FieldsTaxonomicGrouping} (original type "fields_TaxonomicGrouping")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTaxonomicGrouping> allEntitiesTaxonomicGrouping(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTaxonomicGrouping>>> retType = new TypeReference<List<Map<String,FieldsTaxonomicGrouping>>>() {};
        List<Map<String,FieldsTaxonomicGrouping>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_TaxonomicGrouping", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_TimeSeries</p>
     * <pre>
     * A TimeSeries provides a means to specify a series of experimental data
     * over time by ordering multiple ExperimentalUnits.
     * It has the following fields:
     * =over 4
     * =item source_id
     * The ID of the time series used by the data source.
     * =item name
     * The name of this time series, if any.
     * =item comments
     * Any comments regarding this time series.
     * =item timeUnits
     * The units of time for this time series, e.g. 'seconds', 'hours', or more abstractly, 'number of times culture grown to saturation.'
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTimeSeries FieldsTimeSeries} (original type "fields_TimeSeries")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTimeSeries> getEntityTimeSeries(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTimeSeries>>> retType = new TypeReference<List<Map<String,FieldsTimeSeries>>>() {};
        List<Map<String,FieldsTimeSeries>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_TimeSeries", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_TimeSeries</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTimeSeries FieldsTimeSeries} (original type "fields_TimeSeries")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTimeSeries> queryEntityTimeSeries(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTimeSeries>>> retType = new TypeReference<List<Map<String,FieldsTimeSeries>>>() {};
        List<Map<String,FieldsTimeSeries>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_TimeSeries", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_TimeSeries</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTimeSeries FieldsTimeSeries} (original type "fields_TimeSeries")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTimeSeries> allEntitiesTimeSeries(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTimeSeries>>> retType = new TypeReference<List<Map<String,FieldsTimeSeries>>>() {};
        List<Map<String,FieldsTimeSeries>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_TimeSeries", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Trait</p>
     * <pre>
     * A Trait is a phenotypic quality that can be measured or observed for an observational unit.  Examples include height, sugar content, color, or cold tolerance.
     * It has the following fields:
     * =over 4
     * =item trait_name
     * Text name or description of the trait
     * =item unit_of_measure
     * The units of measure used when determining this trait.  If multiple units of measure are applicable, each has its own row in the database.  
     * =item TO_ID
     * Trait Ontology term ID (http://www.gramene.org/plant-ontology/)
     * =item protocol
     * A thorough description of how the trait was collected, and if a rating, the minimum and maximum values
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTrait FieldsTrait} (original type "fields_Trait")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTrait> getEntityTrait(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTrait>>> retType = new TypeReference<List<Map<String,FieldsTrait>>>() {};
        List<Map<String,FieldsTrait>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Trait", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Trait</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTrait FieldsTrait} (original type "fields_Trait")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTrait> queryEntityTrait(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTrait>>> retType = new TypeReference<List<Map<String,FieldsTrait>>>() {};
        List<Map<String,FieldsTrait>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Trait", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Trait</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTrait FieldsTrait} (original type "fields_Trait")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTrait> allEntitiesTrait(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTrait>>> retType = new TypeReference<List<Map<String,FieldsTrait>>>() {};
        List<Map<String,FieldsTrait>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Trait", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Tree</p>
     * <pre>
     * A tree describes how the sequences in an alignment relate
     * to each other. Most trees are phylogenetic, but some may be based on
     * taxonomy or gene content.
     * It has the following fields:
     * =over 4
     * =item status
     * status of the tree, currently either [i]active[/i], [i]superseded[/i], or [i]bad[/i]
     * =item data_type
     * type of data the tree was built from, usually [i]sequence_alignment[/i]
     * =item timestamp
     * date and time the tree was loaded
     * =item method
     * name of the primary software package or script used to construct the tree
     * =item parameters
     * non-default parameters used as input to the software package or script indicated in the method attribute
     * =item protocol
     * description of the steps taken to construct the tree, or a reference to an external pipeline
     * =item source_id
     * ID of this tree in the source database
     * =item newick
     * NEWICK format string containing the structure of the tree
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTree> getEntityTree(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTree>>> retType = new TypeReference<List<Map<String,FieldsTree>>>() {};
        List<Map<String,FieldsTree>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Tree", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Tree</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTree> queryEntityTree(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTree>>> retType = new TypeReference<List<Map<String,FieldsTree>>>() {};
        List<Map<String,FieldsTree>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Tree", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Tree</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTree> allEntitiesTree(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTree>>> retType = new TypeReference<List<Map<String,FieldsTree>>>() {};
        List<Map<String,FieldsTree>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Tree", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_TreeAttribute</p>
     * <pre>
     * This entity represents an attribute type that can
     * be assigned to a tree. The attribute
     * values are stored in the relationships to the target. The
     * key is the attribute name.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTreeAttribute FieldsTreeAttribute} (original type "fields_TreeAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTreeAttribute> getEntityTreeAttribute(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTreeAttribute>>> retType = new TypeReference<List<Map<String,FieldsTreeAttribute>>>() {};
        List<Map<String,FieldsTreeAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_TreeAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_TreeAttribute</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTreeAttribute FieldsTreeAttribute} (original type "fields_TreeAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTreeAttribute> queryEntityTreeAttribute(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTreeAttribute>>> retType = new TypeReference<List<Map<String,FieldsTreeAttribute>>>() {};
        List<Map<String,FieldsTreeAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_TreeAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_TreeAttribute</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTreeAttribute FieldsTreeAttribute} (original type "fields_TreeAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTreeAttribute> allEntitiesTreeAttribute(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTreeAttribute>>> retType = new TypeReference<List<Map<String,FieldsTreeAttribute>>>() {};
        List<Map<String,FieldsTreeAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_TreeAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_TreeNodeAttribute</p>
     * <pre>
     * This entity represents an attribute type that can
     * be assigned to a node. The attribute
     * values are stored in the relationships to the target. The
     * key is the attribute name.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTreeNodeAttribute FieldsTreeNodeAttribute} (original type "fields_TreeNodeAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTreeNodeAttribute> getEntityTreeNodeAttribute(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTreeNodeAttribute>>> retType = new TypeReference<List<Map<String,FieldsTreeNodeAttribute>>>() {};
        List<Map<String,FieldsTreeNodeAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_TreeNodeAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_TreeNodeAttribute</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTreeNodeAttribute FieldsTreeNodeAttribute} (original type "fields_TreeNodeAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTreeNodeAttribute> queryEntityTreeNodeAttribute(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTreeNodeAttribute>>> retType = new TypeReference<List<Map<String,FieldsTreeNodeAttribute>>>() {};
        List<Map<String,FieldsTreeNodeAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_TreeNodeAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_TreeNodeAttribute</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsTreeNodeAttribute FieldsTreeNodeAttribute} (original type "fields_TreeNodeAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsTreeNodeAttribute> allEntitiesTreeNodeAttribute(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsTreeNodeAttribute>>> retType = new TypeReference<List<Map<String,FieldsTreeNodeAttribute>>>() {};
        List<Map<String,FieldsTreeNodeAttribute>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_TreeNodeAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_entity_Variant</p>
     * <pre>
     * Each subsystem may include the designation of distinct
     * variants.  Thus, there may be three closely-related, but
     * distinguishable forms of histidine degradation.  Each form
     * would be called a "variant", with an associated code, and all
     * genomes implementing a specific variant can easily be accessed. The ID
     * is an MD5 of the subsystem name followed by the variant code.
     * It has the following fields:
     * =over 4
     * =item role_rule
     * a space-delimited list of role IDs, in alphabetical order, that represents a possible list of non-auxiliary roles applicable to this variant. The roles are identified by their abbreviations. A variant may have multiple role rules.
     * =item code
     * the variant code all by itself
     * =item type
     * variant type indicating the quality of the subsystem support. A type of "vacant" means that the subsystem does not appear to be implemented by the variant. A type of "incomplete" means that the subsystem appears to be missing many reactions. In all other cases, the type is "normal".
     * =item comment
     * commentary text about the variant
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsVariant FieldsVariant} (original type "fields_Variant")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsVariant> getEntityVariant(List<String> ids, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fields);
        TypeReference<List<Map<String,FieldsVariant>>> retType = new TypeReference<List<Map<String,FieldsVariant>>>() {};
        List<Map<String,FieldsVariant>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_entity_Variant", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: query_entity_Variant</p>
     * <pre>
     * </pre>
     * @param   qry   instance of list of tuple of size 3: String, String, String
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsVariant FieldsVariant} (original type "fields_Variant")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsVariant> queryEntityVariant(List<Tuple3<String, String, String>> qry, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(qry);
        args.add(fields);
        TypeReference<List<Map<String,FieldsVariant>>> retType = new TypeReference<List<Map<String,FieldsVariant>>>() {};
        List<Map<String,FieldsVariant>> res = caller.jsonrpcCall("CDMI_EntityAPI.query_entity_Variant", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: all_entities_Variant</p>
     * <pre>
     * </pre>
     * @param   start   instance of Long
     * @param   count   instance of Long
     * @param   fields   instance of list of String
     * @return   instance of mapping from String to type {@link us.kbase.cdmientityapi.FieldsVariant FieldsVariant} (original type "fields_Variant")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,FieldsVariant> allEntitiesVariant(Long start, Long count, List<String> fields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(start);
        args.add(count);
        args.add(fields);
        TypeReference<List<Map<String,FieldsVariant>>> retType = new TypeReference<List<Map<String,FieldsVariant>>>() {};
        List<Map<String,FieldsVariant>> res = caller.jsonrpcCall("CDMI_EntityAPI.all_entities_Variant", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_AffectsLevelOf</p>
     * <pre>
     * This relationship indicates the expression level of an atomic regulon
     * for a given experiment.
     * It has the following fields:
     * =over 4
     * =item level
     * Indication of whether the feature is expressed (1), not expressed (-1), or unknown (0).
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment"), type {@link us.kbase.cdmientityapi.FieldsAffectsLevelOf FieldsAffectsLevelOf} (original type "fields_AffectsLevelOf"), type {@link us.kbase.cdmientityapi.FieldsAtomicRegulon FieldsAtomicRegulon} (original type "fields_AtomicRegulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperiment, FieldsAffectsLevelOf, FieldsAtomicRegulon>> getRelationshipAffectsLevelOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperiment, FieldsAffectsLevelOf, FieldsAtomicRegulon>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperiment, FieldsAffectsLevelOf, FieldsAtomicRegulon>>>>() {};
        List<List<Tuple3<FieldsExperiment, FieldsAffectsLevelOf, FieldsAtomicRegulon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_AffectsLevelOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsAffectedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAtomicRegulon FieldsAtomicRegulon} (original type "fields_AtomicRegulon"), type {@link us.kbase.cdmientityapi.FieldsAffectsLevelOf FieldsAffectsLevelOf} (original type "fields_AffectsLevelOf"), type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAtomicRegulon, FieldsAffectsLevelOf, FieldsExperiment>> getRelationshipIsAffectedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAtomicRegulon, FieldsAffectsLevelOf, FieldsExperiment>>>> retType = new TypeReference<List<List<Tuple3<FieldsAtomicRegulon, FieldsAffectsLevelOf, FieldsExperiment>>>>() {};
        List<List<Tuple3<FieldsAtomicRegulon, FieldsAffectsLevelOf, FieldsExperiment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsAffectedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Aligned</p>
     * <pre>
     * This relationship connects an alignment to the database
     * from which it was generated.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source"), type {@link us.kbase.cdmientityapi.FieldsAligned FieldsAligned} (original type "fields_Aligned"), type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSource, FieldsAligned, FieldsAlignment>> getRelationshipAligned(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSource, FieldsAligned, FieldsAlignment>>>> retType = new TypeReference<List<List<Tuple3<FieldsSource, FieldsAligned, FieldsAlignment>>>>() {};
        List<List<Tuple3<FieldsSource, FieldsAligned, FieldsAlignment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Aligned", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_WasAlignedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment"), type {@link us.kbase.cdmientityapi.FieldsAligned FieldsAligned} (original type "fields_Aligned"), type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignment, FieldsAligned, FieldsSource>> getRelationshipWasAlignedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignment, FieldsAligned, FieldsSource>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignment, FieldsAligned, FieldsSource>>>>() {};
        List<List<Tuple3<FieldsAlignment, FieldsAligned, FieldsSource>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_WasAlignedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_AssertsFunctionFor</p>
     * <pre>
     * Sources (users) can make assertions about protein sequence function.
     * The assertion is associated with an external identifier.
     * It has the following fields:
     * =over 4
     * =item function
     * text of the assertion made about the identifier. It may be an empty string, indicating the function is unknown.
     * =item external_id
     * external identifier used in making the assertion
     * =item organism
     * organism name associated with this assertion. If the assertion is not associated with a specific organism, this will be an empty string.
     * =item gi_number
     * NCBI GI number associated with the asserted identifier
     * =item release_date
     * date and time the assertion was downloaded
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source"), type {@link us.kbase.cdmientityapi.FieldsAssertsFunctionFor FieldsAssertsFunctionFor} (original type "fields_AssertsFunctionFor"), type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSource, FieldsAssertsFunctionFor, FieldsProteinSequence>> getRelationshipAssertsFunctionFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSource, FieldsAssertsFunctionFor, FieldsProteinSequence>>>> retType = new TypeReference<List<List<Tuple3<FieldsSource, FieldsAssertsFunctionFor, FieldsProteinSequence>>>>() {};
        List<List<Tuple3<FieldsSource, FieldsAssertsFunctionFor, FieldsProteinSequence>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_AssertsFunctionFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasAssertedFunctionFrom</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence"), type {@link us.kbase.cdmientityapi.FieldsAssertsFunctionFor FieldsAssertsFunctionFor} (original type "fields_AssertsFunctionFor"), type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProteinSequence, FieldsAssertsFunctionFor, FieldsSource>> getRelationshipHasAssertedFunctionFrom(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsAssertsFunctionFor, FieldsSource>>>> retType = new TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsAssertsFunctionFor, FieldsSource>>>>() {};
        List<List<Tuple3<FieldsProteinSequence, FieldsAssertsFunctionFor, FieldsSource>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasAssertedFunctionFrom", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_AssociationFeature</p>
     * <pre>
     * The AssociationFeature relationship links associations to
     * the features that encode their component proteins.
     * It has the following fields:
     * =over 4
     * =item stoichiometry
     * Stoichiometry, if applicable (e.g., for a curated complex.
     * =item strength
     * Optional numeric measure of strength of the association (e.g., kD or relative estimate of binding affinity)
     * =item rank
     * Numbered starting at 1 within an Association, if directional.  Meaning is method-dependent; e.g., for associations derived from pulldown data, rank 1 should be assigned to the bait.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAssociation FieldsAssociation} (original type "fields_Association"), type {@link us.kbase.cdmientityapi.FieldsAssociationFeature FieldsAssociationFeature} (original type "fields_AssociationFeature"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAssociation, FieldsAssociationFeature, FieldsFeature>> getRelationshipAssociationFeature(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAssociation, FieldsAssociationFeature, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsAssociation, FieldsAssociationFeature, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsAssociation, FieldsAssociationFeature, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_AssociationFeature", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_FeatureInteractsIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsAssociationFeature FieldsAssociationFeature} (original type "fields_AssociationFeature"), type {@link us.kbase.cdmientityapi.FieldsAssociation FieldsAssociation} (original type "fields_Association")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsAssociationFeature, FieldsAssociation>> getRelationshipFeatureInteractsIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsAssociationFeature, FieldsAssociation>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsAssociationFeature, FieldsAssociation>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsAssociationFeature, FieldsAssociation>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_FeatureInteractsIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_CompoundMeasuredBy</p>
     * <pre>
     * Denotes the compound that a measurement quantifies.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound"), type {@link us.kbase.cdmientityapi.FieldsCompoundMeasuredBy FieldsCompoundMeasuredBy} (original type "fields_CompoundMeasuredBy"), type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompound, FieldsCompoundMeasuredBy, FieldsMeasurement>> getRelationshipCompoundMeasuredBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompound, FieldsCompoundMeasuredBy, FieldsMeasurement>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompound, FieldsCompoundMeasuredBy, FieldsMeasurement>>>>() {};
        List<List<Tuple3<FieldsCompound, FieldsCompoundMeasuredBy, FieldsMeasurement>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_CompoundMeasuredBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_MeasuresCompound</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement"), type {@link us.kbase.cdmientityapi.FieldsCompoundMeasuredBy FieldsCompoundMeasuredBy} (original type "fields_CompoundMeasuredBy"), type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsMeasurement, FieldsCompoundMeasuredBy, FieldsCompound>> getRelationshipMeasuresCompound(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsCompoundMeasuredBy, FieldsCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsCompoundMeasuredBy, FieldsCompound>>>>() {};
        List<List<Tuple3<FieldsMeasurement, FieldsCompoundMeasuredBy, FieldsCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_MeasuresCompound", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Concerns</p>
     * <pre>
     * This relationship connects a publication to the protein
     * sequences it describes.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication"), type {@link us.kbase.cdmientityapi.FieldsConcerns FieldsConcerns} (original type "fields_Concerns"), type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPublication, FieldsConcerns, FieldsProteinSequence>> getRelationshipConcerns(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPublication, FieldsConcerns, FieldsProteinSequence>>>> retType = new TypeReference<List<List<Tuple3<FieldsPublication, FieldsConcerns, FieldsProteinSequence>>>>() {};
        List<List<Tuple3<FieldsPublication, FieldsConcerns, FieldsProteinSequence>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Concerns", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsATopicOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence"), type {@link us.kbase.cdmientityapi.FieldsConcerns FieldsConcerns} (original type "fields_Concerns"), type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProteinSequence, FieldsConcerns, FieldsPublication>> getRelationshipIsATopicOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsConcerns, FieldsPublication>>>> retType = new TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsConcerns, FieldsPublication>>>>() {};
        List<List<Tuple3<FieldsProteinSequence, FieldsConcerns, FieldsPublication>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsATopicOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ConsistsOfCompounds</p>
     * <pre>
     * This relationship defines the subcompounds that make up a
     * compound. For example, CoCl2-6H2O is made up of 1 Co2+, 2 Cl-, and
     * 6 H2O.
     * It has the following fields:
     * =over 4
     * =item molar_ratio
     * Number of molecules of the subcompound that make up the compound. A -1 in this field signifies that although the subcompound is present in the compound, the molar ratio is unknown.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound"), type {@link us.kbase.cdmientityapi.FieldsConsistsOfCompounds FieldsConsistsOfCompounds} (original type "fields_ConsistsOfCompounds"), type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompound, FieldsConsistsOfCompounds, FieldsCompound>> getRelationshipConsistsOfCompounds(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompound, FieldsConsistsOfCompounds, FieldsCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompound, FieldsConsistsOfCompounds, FieldsCompound>>>>() {};
        List<List<Tuple3<FieldsCompound, FieldsConsistsOfCompounds, FieldsCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ConsistsOfCompounds", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ComponentOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound"), type {@link us.kbase.cdmientityapi.FieldsConsistsOfCompounds FieldsConsistsOfCompounds} (original type "fields_ConsistsOfCompounds"), type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompound, FieldsConsistsOfCompounds, FieldsCompound>> getRelationshipComponentOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompound, FieldsConsistsOfCompounds, FieldsCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompound, FieldsConsistsOfCompounds, FieldsCompound>>>>() {};
        List<List<Tuple3<FieldsCompound, FieldsConsistsOfCompounds, FieldsCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ComponentOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Contains</p>
     * <pre>
     * This relationship connects a subsystem spreadsheet cell to the features
     * that occur in it. A feature may occur in many machine roles and a
     * machine role may contain many features. The subsystem annotation
     * process is essentially the maintenance of this relationship.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSSCell FieldsSSCell} (original type "fields_SSCell"), type {@link us.kbase.cdmientityapi.FieldsContains FieldsContains} (original type "fields_Contains"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSSCell, FieldsContains, FieldsFeature>> getRelationshipContains(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSSCell, FieldsContains, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsSSCell, FieldsContains, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsSSCell, FieldsContains, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Contains", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsContainedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsContains FieldsContains} (original type "fields_Contains"), type {@link us.kbase.cdmientityapi.FieldsSSCell FieldsSSCell} (original type "fields_SSCell")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsContains, FieldsSSCell>> getRelationshipIsContainedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsContains, FieldsSSCell>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsContains, FieldsSSCell>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsContains, FieldsSSCell>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsContainedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ContainsAlignedDNA</p>
     * <pre>
     * This relationship connects a nucleotide alignment row to the
     * contig sequences from which its components are formed.
     * It has the following fields:
     * =over 4
     * =item index_in_concatenation
     * 1-based ordinal position in the alignment row of this nucleotide sequence
     * =item beg_pos_in_parent
     * 1-based position in the contig sequence of the first nucleotide that appears in the alignment
     * =item end_pos_in_parent
     * 1-based position in the contig sequence of the last nucleotide that appears in the alignment
     * =item parent_seq_len
     * length of original sequence
     * =item beg_pos_aln
     * the 1-based column index in the alignment where this nucleotide sequence begins
     * =item end_pos_aln
     * the 1-based column index in the alignment where this nucleotide sequence ends
     * =item kb_feature_id
     * ID of the feature relevant to this sequence, or an empty string if the sequence is not specific to a genome
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignmentRow FieldsAlignmentRow} (original type "fields_AlignmentRow"), type {@link us.kbase.cdmientityapi.FieldsContainsAlignedDNA FieldsContainsAlignedDNA} (original type "fields_ContainsAlignedDNA"), type {@link us.kbase.cdmientityapi.FieldsContigSequence FieldsContigSequence} (original type "fields_ContigSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignmentRow, FieldsContainsAlignedDNA, FieldsContigSequence>> getRelationshipContainsAlignedDNA(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignmentRow, FieldsContainsAlignedDNA, FieldsContigSequence>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignmentRow, FieldsContainsAlignedDNA, FieldsContigSequence>>>>() {};
        List<List<Tuple3<FieldsAlignmentRow, FieldsContainsAlignedDNA, FieldsContigSequence>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ContainsAlignedDNA", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsAlignedDNAComponentOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsContigSequence FieldsContigSequence} (original type "fields_ContigSequence"), type {@link us.kbase.cdmientityapi.FieldsContainsAlignedDNA FieldsContainsAlignedDNA} (original type "fields_ContainsAlignedDNA"), type {@link us.kbase.cdmientityapi.FieldsAlignmentRow FieldsAlignmentRow} (original type "fields_AlignmentRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsContigSequence, FieldsContainsAlignedDNA, FieldsAlignmentRow>> getRelationshipIsAlignedDNAComponentOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsContigSequence, FieldsContainsAlignedDNA, FieldsAlignmentRow>>>> retType = new TypeReference<List<List<Tuple3<FieldsContigSequence, FieldsContainsAlignedDNA, FieldsAlignmentRow>>>>() {};
        List<List<Tuple3<FieldsContigSequence, FieldsContainsAlignedDNA, FieldsAlignmentRow>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsAlignedDNAComponentOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ContainsAlignedProtein</p>
     * <pre>
     * This relationship connects a protein alignment row to the
     * protein sequences from which its components are formed.
     * It has the following fields:
     * =over 4
     * =item index_in_concatenation
     * 1-based ordinal position in the alignment row of this protein sequence
     * =item beg_pos_in_parent
     * 1-based position in the protein sequence of the first amino acid that appears in the alignment
     * =item end_pos_in_parent
     * 1-based position in the protein sequence of the last amino acid that appears in the alignment
     * =item parent_seq_len
     * length of original sequence
     * =item beg_pos_aln
     * the 1-based column index in the alignment where this protein sequence begins
     * =item end_pos_aln
     * the 1-based column index in the alignment where this protein sequence ends
     * =item kb_feature_id
     * ID of the feature relevant to this protein, or an empty string if the protein is not specific to a genome
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignmentRow FieldsAlignmentRow} (original type "fields_AlignmentRow"), type {@link us.kbase.cdmientityapi.FieldsContainsAlignedProtein FieldsContainsAlignedProtein} (original type "fields_ContainsAlignedProtein"), type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignmentRow, FieldsContainsAlignedProtein, FieldsProteinSequence>> getRelationshipContainsAlignedProtein(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignmentRow, FieldsContainsAlignedProtein, FieldsProteinSequence>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignmentRow, FieldsContainsAlignedProtein, FieldsProteinSequence>>>>() {};
        List<List<Tuple3<FieldsAlignmentRow, FieldsContainsAlignedProtein, FieldsProteinSequence>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ContainsAlignedProtein", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsAlignedProteinComponentOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence"), type {@link us.kbase.cdmientityapi.FieldsContainsAlignedProtein FieldsContainsAlignedProtein} (original type "fields_ContainsAlignedProtein"), type {@link us.kbase.cdmientityapi.FieldsAlignmentRow FieldsAlignmentRow} (original type "fields_AlignmentRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProteinSequence, FieldsContainsAlignedProtein, FieldsAlignmentRow>> getRelationshipIsAlignedProteinComponentOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsContainsAlignedProtein, FieldsAlignmentRow>>>> retType = new TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsContainsAlignedProtein, FieldsAlignmentRow>>>>() {};
        List<List<Tuple3<FieldsProteinSequence, FieldsContainsAlignedProtein, FieldsAlignmentRow>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsAlignedProteinComponentOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ContainsExperimentalUnit</p>
     * <pre>
     * Experimental units may be collected into groups, such as assay
     * plates. This relationship describes which experimenal units belong to
     * which groups.
     * It has the following fields:
     * =over 4
     * =item location
     * The location, if any, of the experimental unit in the group. Often a plate locator, e.g. 'G11' for 96 well plates.
     * =item groupMeta
     * Denotes that the associated ExperimentalUnit's data measures the group as a whole - for example, summary statistics.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperimentalUnitGroup FieldsExperimentalUnitGroup} (original type "fields_ExperimentalUnitGroup"), type {@link us.kbase.cdmientityapi.FieldsContainsExperimentalUnit FieldsContainsExperimentalUnit} (original type "fields_ContainsExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperimentalUnitGroup, FieldsContainsExperimentalUnit, FieldsExperimentalUnit>> getRelationshipContainsExperimentalUnit(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperimentalUnitGroup, FieldsContainsExperimentalUnit, FieldsExperimentalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperimentalUnitGroup, FieldsContainsExperimentalUnit, FieldsExperimentalUnit>>>>() {};
        List<List<Tuple3<FieldsExperimentalUnitGroup, FieldsContainsExperimentalUnit, FieldsExperimentalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ContainsExperimentalUnit", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_GroupedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsContainsExperimentalUnit FieldsContainsExperimentalUnit} (original type "fields_ContainsExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsExperimentalUnitGroup FieldsExperimentalUnitGroup} (original type "fields_ExperimentalUnitGroup")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperimentalUnit, FieldsContainsExperimentalUnit, FieldsExperimentalUnitGroup>> getRelationshipGroupedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsContainsExperimentalUnit, FieldsExperimentalUnitGroup>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsContainsExperimentalUnit, FieldsExperimentalUnitGroup>>>>() {};
        List<List<Tuple3<FieldsExperimentalUnit, FieldsContainsExperimentalUnit, FieldsExperimentalUnitGroup>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_GroupedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Controls</p>
     * <pre>
     * This relationship connects a coregulated set to the
     * features that are used as its transcription factors.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsControls FieldsControls} (original type "fields_Controls"), type {@link us.kbase.cdmientityapi.FieldsCoregulatedSet FieldsCoregulatedSet} (original type "fields_CoregulatedSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsControls, FieldsCoregulatedSet>> getRelationshipControls(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsControls, FieldsCoregulatedSet>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsControls, FieldsCoregulatedSet>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsControls, FieldsCoregulatedSet>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Controls", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsControlledUsing</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCoregulatedSet FieldsCoregulatedSet} (original type "fields_CoregulatedSet"), type {@link us.kbase.cdmientityapi.FieldsControls FieldsControls} (original type "fields_Controls"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCoregulatedSet, FieldsControls, FieldsFeature>> getRelationshipIsControlledUsing(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCoregulatedSet, FieldsControls, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsCoregulatedSet, FieldsControls, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsCoregulatedSet, FieldsControls, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsControlledUsing", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_DefaultControlSample</p>
     * <pre>
     * The Default control for samples to compare against.  (Log2 measurments of Test Sample)/(Log2 measurements of Default Control).
     * Really minus instead of divide since the values are already in Log2 Space.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsDefaultControlSample FieldsDefaultControlSample} (original type "fields_DefaultControlSample"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsDefaultControlSample, FieldsSample>> getRelationshipDefaultControlSample(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsDefaultControlSample, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsDefaultControlSample, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsDefaultControlSample, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_DefaultControlSample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SamplesDefaultControl</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsDefaultControlSample FieldsDefaultControlSample} (original type "fields_DefaultControlSample"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsDefaultControlSample, FieldsSample>> getRelationshipSamplesDefaultControl(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsDefaultControlSample, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsDefaultControlSample, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsDefaultControlSample, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SamplesDefaultControl", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Describes</p>
     * <pre>
     * This relationship connects a subsystem to the individual
     * variants used to implement it. Each variant contains a slightly
     * different subset of the roles in the parent subsystem.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem"), type {@link us.kbase.cdmientityapi.FieldsDescribes FieldsDescribes} (original type "fields_Describes"), type {@link us.kbase.cdmientityapi.FieldsVariant FieldsVariant} (original type "fields_Variant")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSubsystem, FieldsDescribes, FieldsVariant>> getRelationshipDescribes(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsDescribes, FieldsVariant>>>> retType = new TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsDescribes, FieldsVariant>>>>() {};
        List<List<Tuple3<FieldsSubsystem, FieldsDescribes, FieldsVariant>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Describes", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsDescribedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsVariant FieldsVariant} (original type "fields_Variant"), type {@link us.kbase.cdmientityapi.FieldsDescribes FieldsDescribes} (original type "fields_Describes"), type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsVariant, FieldsDescribes, FieldsSubsystem>> getRelationshipIsDescribedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsVariant, FieldsDescribes, FieldsSubsystem>>>> retType = new TypeReference<List<List<Tuple3<FieldsVariant, FieldsDescribes, FieldsSubsystem>>>>() {};
        List<List<Tuple3<FieldsVariant, FieldsDescribes, FieldsSubsystem>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsDescribedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_DescribesAlignment</p>
     * <pre>
     * This relationship connects an alignment to its free-form
     * attributes.
     * It has the following fields:
     * =over 4
     * =item value
     * value of this attribute
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignmentAttribute FieldsAlignmentAttribute} (original type "fields_AlignmentAttribute"), type {@link us.kbase.cdmientityapi.FieldsDescribesAlignment FieldsDescribesAlignment} (original type "fields_DescribesAlignment"), type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignmentAttribute, FieldsDescribesAlignment, FieldsAlignment>> getRelationshipDescribesAlignment(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignmentAttribute, FieldsDescribesAlignment, FieldsAlignment>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignmentAttribute, FieldsDescribesAlignment, FieldsAlignment>>>>() {};
        List<List<Tuple3<FieldsAlignmentAttribute, FieldsDescribesAlignment, FieldsAlignment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_DescribesAlignment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasAlignmentAttribute</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment"), type {@link us.kbase.cdmientityapi.FieldsDescribesAlignment FieldsDescribesAlignment} (original type "fields_DescribesAlignment"), type {@link us.kbase.cdmientityapi.FieldsAlignmentAttribute FieldsAlignmentAttribute} (original type "fields_AlignmentAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignment, FieldsDescribesAlignment, FieldsAlignmentAttribute>> getRelationshipHasAlignmentAttribute(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignment, FieldsDescribesAlignment, FieldsAlignmentAttribute>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignment, FieldsDescribesAlignment, FieldsAlignmentAttribute>>>>() {};
        List<List<Tuple3<FieldsAlignment, FieldsDescribesAlignment, FieldsAlignmentAttribute>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasAlignmentAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_DescribesMeasurement</p>
     * <pre>
     * The DescribesMeasurement relationship specifies a description
     * for a particular measurement.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsMeasurementDescription FieldsMeasurementDescription} (original type "fields_MeasurementDescription"), type {@link us.kbase.cdmientityapi.FieldsDescribesMeasurement FieldsDescribesMeasurement} (original type "fields_DescribesMeasurement"), type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsMeasurementDescription, FieldsDescribesMeasurement, FieldsMeasurement>> getRelationshipDescribesMeasurement(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsMeasurementDescription, FieldsDescribesMeasurement, FieldsMeasurement>>>> retType = new TypeReference<List<List<Tuple3<FieldsMeasurementDescription, FieldsDescribesMeasurement, FieldsMeasurement>>>>() {};
        List<List<Tuple3<FieldsMeasurementDescription, FieldsDescribesMeasurement, FieldsMeasurement>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_DescribesMeasurement", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsDefinedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement"), type {@link us.kbase.cdmientityapi.FieldsDescribesMeasurement FieldsDescribesMeasurement} (original type "fields_DescribesMeasurement"), type {@link us.kbase.cdmientityapi.FieldsMeasurementDescription FieldsMeasurementDescription} (original type "fields_MeasurementDescription")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsMeasurement, FieldsDescribesMeasurement, FieldsMeasurementDescription>> getRelationshipIsDefinedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsDescribesMeasurement, FieldsMeasurementDescription>>>> retType = new TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsDescribesMeasurement, FieldsMeasurementDescription>>>>() {};
        List<List<Tuple3<FieldsMeasurement, FieldsDescribesMeasurement, FieldsMeasurementDescription>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsDefinedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_DescribesTree</p>
     * <pre>
     * This relationship connects a tree to its free-form
     * attributes.
     * It has the following fields:
     * =over 4
     * =item value
     * value of this attribute
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTreeAttribute FieldsTreeAttribute} (original type "fields_TreeAttribute"), type {@link us.kbase.cdmientityapi.FieldsDescribesTree FieldsDescribesTree} (original type "fields_DescribesTree"), type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTreeAttribute, FieldsDescribesTree, FieldsTree>> getRelationshipDescribesTree(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTreeAttribute, FieldsDescribesTree, FieldsTree>>>> retType = new TypeReference<List<List<Tuple3<FieldsTreeAttribute, FieldsDescribesTree, FieldsTree>>>>() {};
        List<List<Tuple3<FieldsTreeAttribute, FieldsDescribesTree, FieldsTree>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_DescribesTree", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasTreeAttribute</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree"), type {@link us.kbase.cdmientityapi.FieldsDescribesTree FieldsDescribesTree} (original type "fields_DescribesTree"), type {@link us.kbase.cdmientityapi.FieldsTreeAttribute FieldsTreeAttribute} (original type "fields_TreeAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTree, FieldsDescribesTree, FieldsTreeAttribute>> getRelationshipHasTreeAttribute(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTree, FieldsDescribesTree, FieldsTreeAttribute>>>> retType = new TypeReference<List<List<Tuple3<FieldsTree, FieldsDescribesTree, FieldsTreeAttribute>>>>() {};
        List<List<Tuple3<FieldsTree, FieldsDescribesTree, FieldsTreeAttribute>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasTreeAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_DescribesTreeNode</p>
     * <pre>
     * This relationship connects an tree to the free-form
     * attributes of its nodes.
     * It has the following fields:
     * =over 4
     * =item value
     * value of this attribute
     * =item node_id
     * ID of the node described by the attribute
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTreeNodeAttribute FieldsTreeNodeAttribute} (original type "fields_TreeNodeAttribute"), type {@link us.kbase.cdmientityapi.FieldsDescribesTreeNode FieldsDescribesTreeNode} (original type "fields_DescribesTreeNode"), type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTreeNodeAttribute, FieldsDescribesTreeNode, FieldsTree>> getRelationshipDescribesTreeNode(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTreeNodeAttribute, FieldsDescribesTreeNode, FieldsTree>>>> retType = new TypeReference<List<List<Tuple3<FieldsTreeNodeAttribute, FieldsDescribesTreeNode, FieldsTree>>>>() {};
        List<List<Tuple3<FieldsTreeNodeAttribute, FieldsDescribesTreeNode, FieldsTree>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_DescribesTreeNode", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasNodeAttribute</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree"), type {@link us.kbase.cdmientityapi.FieldsDescribesTreeNode FieldsDescribesTreeNode} (original type "fields_DescribesTreeNode"), type {@link us.kbase.cdmientityapi.FieldsTreeNodeAttribute FieldsTreeNodeAttribute} (original type "fields_TreeNodeAttribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTree, FieldsDescribesTreeNode, FieldsTreeNodeAttribute>> getRelationshipHasNodeAttribute(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTree, FieldsDescribesTreeNode, FieldsTreeNodeAttribute>>>> retType = new TypeReference<List<List<Tuple3<FieldsTree, FieldsDescribesTreeNode, FieldsTreeNodeAttribute>>>>() {};
        List<List<Tuple3<FieldsTree, FieldsDescribesTreeNode, FieldsTreeNodeAttribute>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasNodeAttribute", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_DetectedWithMethod</p>
     * <pre>
     * The DetectedWithMethod relationship describes which
     * protein-protein associations were detected or annotated by
     * particular methods
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAssociationDetectionType FieldsAssociationDetectionType} (original type "fields_AssociationDetectionType"), type {@link us.kbase.cdmientityapi.FieldsDetectedWithMethod FieldsDetectedWithMethod} (original type "fields_DetectedWithMethod"), type {@link us.kbase.cdmientityapi.FieldsAssociation FieldsAssociation} (original type "fields_Association")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAssociationDetectionType, FieldsDetectedWithMethod, FieldsAssociation>> getRelationshipDetectedWithMethod(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAssociationDetectionType, FieldsDetectedWithMethod, FieldsAssociation>>>> retType = new TypeReference<List<List<Tuple3<FieldsAssociationDetectionType, FieldsDetectedWithMethod, FieldsAssociation>>>>() {};
        List<List<Tuple3<FieldsAssociationDetectionType, FieldsDetectedWithMethod, FieldsAssociation>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_DetectedWithMethod", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_DetectedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAssociation FieldsAssociation} (original type "fields_Association"), type {@link us.kbase.cdmientityapi.FieldsDetectedWithMethod FieldsDetectedWithMethod} (original type "fields_DetectedWithMethod"), type {@link us.kbase.cdmientityapi.FieldsAssociationDetectionType FieldsAssociationDetectionType} (original type "fields_AssociationDetectionType")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAssociation, FieldsDetectedWithMethod, FieldsAssociationDetectionType>> getRelationshipDetectedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAssociation, FieldsDetectedWithMethod, FieldsAssociationDetectionType>>>> retType = new TypeReference<List<List<Tuple3<FieldsAssociation, FieldsDetectedWithMethod, FieldsAssociationDetectionType>>>>() {};
        List<List<Tuple3<FieldsAssociation, FieldsDetectedWithMethod, FieldsAssociationDetectionType>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_DetectedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Displays</p>
     * <pre>
     * This relationship connects a diagram to its reactions. A
     * diagram shows multiple reactions, and a reaction can be on many
     * diagrams.
     * It has the following fields:
     * =over 4
     * =item location
     * Location of the reaction's node on the diagram.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsDiagram FieldsDiagram} (original type "fields_Diagram"), type {@link us.kbase.cdmientityapi.FieldsDisplays FieldsDisplays} (original type "fields_Displays"), type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsDiagram, FieldsDisplays, FieldsReaction>> getRelationshipDisplays(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsDiagram, FieldsDisplays, FieldsReaction>>>> retType = new TypeReference<List<List<Tuple3<FieldsDiagram, FieldsDisplays, FieldsReaction>>>>() {};
        List<List<Tuple3<FieldsDiagram, FieldsDisplays, FieldsReaction>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Displays", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsDisplayedOn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction"), type {@link us.kbase.cdmientityapi.FieldsDisplays FieldsDisplays} (original type "fields_Displays"), type {@link us.kbase.cdmientityapi.FieldsDiagram FieldsDiagram} (original type "fields_Diagram")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsReaction, FieldsDisplays, FieldsDiagram>> getRelationshipIsDisplayedOn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsReaction, FieldsDisplays, FieldsDiagram>>>> retType = new TypeReference<List<List<Tuple3<FieldsReaction, FieldsDisplays, FieldsDiagram>>>>() {};
        List<List<Tuple3<FieldsReaction, FieldsDisplays, FieldsDiagram>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsDisplayedOn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Encompasses</p>
     * <pre>
     * This relationship connects a feature to a related
     * feature; for example, it would connect a gene to its
     * constituent splice variants, and the splice variants to their
     * exons.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsEncompasses FieldsEncompasses} (original type "fields_Encompasses"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsEncompasses, FieldsFeature>> getRelationshipEncompasses(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsEncompasses, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsEncompasses, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsEncompasses, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Encompasses", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsEncompassedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsEncompasses FieldsEncompasses} (original type "fields_Encompasses"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsEncompasses, FieldsFeature>> getRelationshipIsEncompassedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsEncompasses, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsEncompasses, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsEncompasses, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsEncompassedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_EvaluatedIn</p>
     * <pre>
     * The EvaluatedIn relationship specifies the experimental
     * units performed on a particular strain.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain"), type {@link us.kbase.cdmientityapi.FieldsEvaluatedIn FieldsEvaluatedIn} (original type "fields_EvaluatedIn"), type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsStrain, FieldsEvaluatedIn, FieldsExperimentalUnit>> getRelationshipEvaluatedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsStrain, FieldsEvaluatedIn, FieldsExperimentalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsStrain, FieldsEvaluatedIn, FieldsExperimentalUnit>>>>() {};
        List<List<Tuple3<FieldsStrain, FieldsEvaluatedIn, FieldsExperimentalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_EvaluatedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IncludesStrain</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsEvaluatedIn FieldsEvaluatedIn} (original type "fields_EvaluatedIn"), type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperimentalUnit, FieldsEvaluatedIn, FieldsStrain>> getRelationshipIncludesStrain(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsEvaluatedIn, FieldsStrain>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsEvaluatedIn, FieldsStrain>>>>() {};
        List<List<Tuple3<FieldsExperimentalUnit, FieldsEvaluatedIn, FieldsStrain>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IncludesStrain", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_FeatureIsTranscriptionFactorFor</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsFeatureIsTranscriptionFactorFor FieldsFeatureIsTranscriptionFactorFor} (original type "fields_FeatureIsTranscriptionFactorFor"), type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsFeatureIsTranscriptionFactorFor, FieldsRegulon>> getRelationshipFeatureIsTranscriptionFactorFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsFeatureIsTranscriptionFactorFor, FieldsRegulon>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsFeatureIsTranscriptionFactorFor, FieldsRegulon>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsFeatureIsTranscriptionFactorFor, FieldsRegulon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_FeatureIsTranscriptionFactorFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasTranscriptionFactorFeature</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon"), type {@link us.kbase.cdmientityapi.FieldsFeatureIsTranscriptionFactorFor FieldsFeatureIsTranscriptionFactorFor} (original type "fields_FeatureIsTranscriptionFactorFor"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulon, FieldsFeatureIsTranscriptionFactorFor, FieldsFeature>> getRelationshipHasTranscriptionFactorFeature(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulon, FieldsFeatureIsTranscriptionFactorFor, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulon, FieldsFeatureIsTranscriptionFactorFor, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsRegulon, FieldsFeatureIsTranscriptionFactorFor, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasTranscriptionFactorFeature", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_FeatureMeasuredBy</p>
     * <pre>
     * Denotes the feature that a measurement quantifies.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsFeatureMeasuredBy FieldsFeatureMeasuredBy} (original type "fields_FeatureMeasuredBy"), type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsFeatureMeasuredBy, FieldsMeasurement>> getRelationshipFeatureMeasuredBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsFeatureMeasuredBy, FieldsMeasurement>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsFeatureMeasuredBy, FieldsMeasurement>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsFeatureMeasuredBy, FieldsMeasurement>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_FeatureMeasuredBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_MeasuresFeature</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement"), type {@link us.kbase.cdmientityapi.FieldsFeatureMeasuredBy FieldsFeatureMeasuredBy} (original type "fields_FeatureMeasuredBy"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsMeasurement, FieldsFeatureMeasuredBy, FieldsFeature>> getRelationshipMeasuresFeature(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsFeatureMeasuredBy, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsFeatureMeasuredBy, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsMeasurement, FieldsFeatureMeasuredBy, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_MeasuresFeature", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Formulated</p>
     * <pre>
     * This relationship connects a coregulated set to the
     * source organization that originally computed it.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source"), type {@link us.kbase.cdmientityapi.FieldsFormulated FieldsFormulated} (original type "fields_Formulated"), type {@link us.kbase.cdmientityapi.FieldsCoregulatedSet FieldsCoregulatedSet} (original type "fields_CoregulatedSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSource, FieldsFormulated, FieldsCoregulatedSet>> getRelationshipFormulated(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSource, FieldsFormulated, FieldsCoregulatedSet>>>> retType = new TypeReference<List<List<Tuple3<FieldsSource, FieldsFormulated, FieldsCoregulatedSet>>>>() {};
        List<List<Tuple3<FieldsSource, FieldsFormulated, FieldsCoregulatedSet>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Formulated", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_WasFormulatedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCoregulatedSet FieldsCoregulatedSet} (original type "fields_CoregulatedSet"), type {@link us.kbase.cdmientityapi.FieldsFormulated FieldsFormulated} (original type "fields_Formulated"), type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCoregulatedSet, FieldsFormulated, FieldsSource>> getRelationshipWasFormulatedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCoregulatedSet, FieldsFormulated, FieldsSource>>>> retType = new TypeReference<List<List<Tuple3<FieldsCoregulatedSet, FieldsFormulated, FieldsSource>>>>() {};
        List<List<Tuple3<FieldsCoregulatedSet, FieldsFormulated, FieldsSource>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_WasFormulatedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_GeneratedLevelsFor</p>
     * <pre>
     * This relationship connects an atomic regulon to a probe set from which experimental
     * data was produced for its features. It contains a vector of the expression levels.
     * It has the following fields:
     * =over 4
     * =item level_vector
     * Vector of expression levels (-1, 0, 1) for the experiments, in sequence order.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProbeSet FieldsProbeSet} (original type "fields_ProbeSet"), type {@link us.kbase.cdmientityapi.FieldsGeneratedLevelsFor FieldsGeneratedLevelsFor} (original type "fields_GeneratedLevelsFor"), type {@link us.kbase.cdmientityapi.FieldsAtomicRegulon FieldsAtomicRegulon} (original type "fields_AtomicRegulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProbeSet, FieldsGeneratedLevelsFor, FieldsAtomicRegulon>> getRelationshipGeneratedLevelsFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProbeSet, FieldsGeneratedLevelsFor, FieldsAtomicRegulon>>>> retType = new TypeReference<List<List<Tuple3<FieldsProbeSet, FieldsGeneratedLevelsFor, FieldsAtomicRegulon>>>>() {};
        List<List<Tuple3<FieldsProbeSet, FieldsGeneratedLevelsFor, FieldsAtomicRegulon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_GeneratedLevelsFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_WasGeneratedFrom</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAtomicRegulon FieldsAtomicRegulon} (original type "fields_AtomicRegulon"), type {@link us.kbase.cdmientityapi.FieldsGeneratedLevelsFor FieldsGeneratedLevelsFor} (original type "fields_GeneratedLevelsFor"), type {@link us.kbase.cdmientityapi.FieldsProbeSet FieldsProbeSet} (original type "fields_ProbeSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAtomicRegulon, FieldsGeneratedLevelsFor, FieldsProbeSet>> getRelationshipWasGeneratedFrom(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAtomicRegulon, FieldsGeneratedLevelsFor, FieldsProbeSet>>>> retType = new TypeReference<List<List<Tuple3<FieldsAtomicRegulon, FieldsGeneratedLevelsFor, FieldsProbeSet>>>>() {};
        List<List<Tuple3<FieldsAtomicRegulon, FieldsGeneratedLevelsFor, FieldsProbeSet>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_WasGeneratedFrom", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_GenomeParentOf</p>
     * <pre>
     * The GenomeParentOf relationship specifies the direct child
     * strains of a specific genome.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsGenomeParentOf FieldsGenomeParentOf} (original type "fields_GenomeParentOf"), type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsGenomeParentOf, FieldsStrain>> getRelationshipGenomeParentOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsGenomeParentOf, FieldsStrain>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsGenomeParentOf, FieldsStrain>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsGenomeParentOf, FieldsStrain>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_GenomeParentOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_DerivedFromGenome</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain"), type {@link us.kbase.cdmientityapi.FieldsGenomeParentOf FieldsGenomeParentOf} (original type "fields_GenomeParentOf"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsStrain, FieldsGenomeParentOf, FieldsGenome>> getRelationshipDerivedFromGenome(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsStrain, FieldsGenomeParentOf, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsStrain, FieldsGenomeParentOf, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsStrain, FieldsGenomeParentOf, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_DerivedFromGenome", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasAliasAssertedFrom</p>
     * <pre>
     * A Source may assert aliases for features.
     * It has the following fields:
     * =over 4
     * =item alias
     * text of the alias for the feature.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsHasAliasAssertedFrom FieldsHasAliasAssertedFrom} (original type "fields_HasAliasAssertedFrom"), type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsHasAliasAssertedFrom, FieldsSource>> getRelationshipHasAliasAssertedFrom(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsHasAliasAssertedFrom, FieldsSource>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsHasAliasAssertedFrom, FieldsSource>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsHasAliasAssertedFrom, FieldsSource>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasAliasAssertedFrom", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_AssertsAliasFor</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source"), type {@link us.kbase.cdmientityapi.FieldsHasAliasAssertedFrom FieldsHasAliasAssertedFrom} (original type "fields_HasAliasAssertedFrom"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSource, FieldsHasAliasAssertedFrom, FieldsFeature>> getRelationshipAssertsAliasFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSource, FieldsHasAliasAssertedFrom, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsSource, FieldsHasAliasAssertedFrom, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsSource, FieldsHasAliasAssertedFrom, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_AssertsAliasFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasCompoundAliasFrom</p>
     * <pre>
     * This relationship connects a source (database or organization)
     * with the compounds for which it has assigned names (aliases).
     * The alias itself is stored as intersection data.
     * It has the following fields:
     * =over 4
     * =item alias
     * alias for the compound assigned by the source
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source"), type {@link us.kbase.cdmientityapi.FieldsHasCompoundAliasFrom FieldsHasCompoundAliasFrom} (original type "fields_HasCompoundAliasFrom"), type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSource, FieldsHasCompoundAliasFrom, FieldsCompound>> getRelationshipHasCompoundAliasFrom(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSource, FieldsHasCompoundAliasFrom, FieldsCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsSource, FieldsHasCompoundAliasFrom, FieldsCompound>>>>() {};
        List<List<Tuple3<FieldsSource, FieldsHasCompoundAliasFrom, FieldsCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasCompoundAliasFrom", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_UsesAliasForCompound</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound"), type {@link us.kbase.cdmientityapi.FieldsHasCompoundAliasFrom FieldsHasCompoundAliasFrom} (original type "fields_HasCompoundAliasFrom"), type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompound, FieldsHasCompoundAliasFrom, FieldsSource>> getRelationshipUsesAliasForCompound(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompound, FieldsHasCompoundAliasFrom, FieldsSource>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompound, FieldsHasCompoundAliasFrom, FieldsSource>>>>() {};
        List<List<Tuple3<FieldsCompound, FieldsHasCompoundAliasFrom, FieldsSource>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_UsesAliasForCompound", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasEffector</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon"), type {@link us.kbase.cdmientityapi.FieldsHasEffector FieldsHasEffector} (original type "fields_HasEffector"), type {@link us.kbase.cdmientityapi.FieldsEffector FieldsEffector} (original type "fields_Effector")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulon, FieldsHasEffector, FieldsEffector>> getRelationshipHasEffector(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulon, FieldsHasEffector, FieldsEffector>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulon, FieldsHasEffector, FieldsEffector>>>>() {};
        List<List<Tuple3<FieldsRegulon, FieldsHasEffector, FieldsEffector>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasEffector", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsEffectorFor</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsEffector FieldsEffector} (original type "fields_Effector"), type {@link us.kbase.cdmientityapi.FieldsHasEffector FieldsHasEffector} (original type "fields_HasEffector"), type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsEffector, FieldsHasEffector, FieldsRegulon>> getRelationshipIsEffectorFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsEffector, FieldsHasEffector, FieldsRegulon>>>> retType = new TypeReference<List<List<Tuple3<FieldsEffector, FieldsHasEffector, FieldsRegulon>>>>() {};
        List<List<Tuple3<FieldsEffector, FieldsHasEffector, FieldsRegulon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsEffectorFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasExperimentalUnit</p>
     * <pre>
     * The HasExperimentalUnit relationship describes which
     * ExperimentalUnits are part of a Experiment.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperimentMeta FieldsExperimentMeta} (original type "fields_ExperimentMeta"), type {@link us.kbase.cdmientityapi.FieldsHasExperimentalUnit FieldsHasExperimentalUnit} (original type "fields_HasExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperimentMeta, FieldsHasExperimentalUnit, FieldsExperimentalUnit>> getRelationshipHasExperimentalUnit(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperimentMeta, FieldsHasExperimentalUnit, FieldsExperimentalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperimentMeta, FieldsHasExperimentalUnit, FieldsExperimentalUnit>>>>() {};
        List<List<Tuple3<FieldsExperimentMeta, FieldsHasExperimentalUnit, FieldsExperimentalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasExperimentalUnit", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsExperimentalUnitOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsHasExperimentalUnit FieldsHasExperimentalUnit} (original type "fields_HasExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsExperimentMeta FieldsExperimentMeta} (original type "fields_ExperimentMeta")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperimentalUnit, FieldsHasExperimentalUnit, FieldsExperimentMeta>> getRelationshipIsExperimentalUnitOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsHasExperimentalUnit, FieldsExperimentMeta>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsHasExperimentalUnit, FieldsExperimentMeta>>>>() {};
        List<List<Tuple3<FieldsExperimentalUnit, FieldsHasExperimentalUnit, FieldsExperimentMeta>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsExperimentalUnitOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasExpressionSample</p>
     * <pre>
     * This relationship indicating the expression samples for an experimental unit.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsHasExpressionSample FieldsHasExpressionSample} (original type "fields_HasExpressionSample"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperimentalUnit, FieldsHasExpressionSample, FieldsSample>> getRelationshipHasExpressionSample(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsHasExpressionSample, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsHasExpressionSample, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsExperimentalUnit, FieldsHasExpressionSample, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasExpressionSample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleBelongsToExperimentalUnit</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsHasExpressionSample FieldsHasExpressionSample} (original type "fields_HasExpressionSample"), type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsHasExpressionSample, FieldsExperimentalUnit>> getRelationshipSampleBelongsToExperimentalUnit(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsHasExpressionSample, FieldsExperimentalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsHasExpressionSample, FieldsExperimentalUnit>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsHasExpressionSample, FieldsExperimentalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleBelongsToExperimentalUnit", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasGenomes</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulogCollection FieldsRegulogCollection} (original type "fields_RegulogCollection"), type {@link us.kbase.cdmientityapi.FieldsHasGenomes FieldsHasGenomes} (original type "fields_HasGenomes"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulogCollection, FieldsHasGenomes, FieldsGenome>> getRelationshipHasGenomes(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulogCollection, FieldsHasGenomes, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulogCollection, FieldsHasGenomes, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsRegulogCollection, FieldsHasGenomes, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasGenomes", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsInRegulogCollection</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsHasGenomes FieldsHasGenomes} (original type "fields_HasGenomes"), type {@link us.kbase.cdmientityapi.FieldsRegulogCollection FieldsRegulogCollection} (original type "fields_RegulogCollection")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsHasGenomes, FieldsRegulogCollection>> getRelationshipIsInRegulogCollection(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsHasGenomes, FieldsRegulogCollection>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsHasGenomes, FieldsRegulogCollection>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsHasGenomes, FieldsRegulogCollection>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsInRegulogCollection", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasIndicatedSignalFrom</p>
     * <pre>
     * This relationship connects an experiment to a feature. The feature
     * expression levels inferred from the experimental results are stored here.
     * It has the following fields:
     * =over 4
     * =item rma_value
     * Normalized expression value for this feature under the experiment's conditions.
     * =item level
     * Indication of whether the feature is expressed (1), not expressed (-1), or unknown (0).
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsHasIndicatedSignalFrom FieldsHasIndicatedSignalFrom} (original type "fields_HasIndicatedSignalFrom"), type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsHasIndicatedSignalFrom, FieldsExperiment>> getRelationshipHasIndicatedSignalFrom(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsHasIndicatedSignalFrom, FieldsExperiment>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsHasIndicatedSignalFrom, FieldsExperiment>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsHasIndicatedSignalFrom, FieldsExperiment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasIndicatedSignalFrom", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IndicatesSignalFor</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment"), type {@link us.kbase.cdmientityapi.FieldsHasIndicatedSignalFrom FieldsHasIndicatedSignalFrom} (original type "fields_HasIndicatedSignalFrom"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperiment, FieldsHasIndicatedSignalFrom, FieldsFeature>> getRelationshipIndicatesSignalFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperiment, FieldsHasIndicatedSignalFrom, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperiment, FieldsHasIndicatedSignalFrom, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsExperiment, FieldsHasIndicatedSignalFrom, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IndicatesSignalFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasKnockoutIn</p>
     * <pre>
     * The HasKnockoutIn relationship specifies the gene knockouts in
     * a particular strain.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain"), type {@link us.kbase.cdmientityapi.FieldsHasKnockoutIn FieldsHasKnockoutIn} (original type "fields_HasKnockoutIn"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsStrain, FieldsHasKnockoutIn, FieldsFeature>> getRelationshipHasKnockoutIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsStrain, FieldsHasKnockoutIn, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsStrain, FieldsHasKnockoutIn, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsStrain, FieldsHasKnockoutIn, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasKnockoutIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_KnockedOutIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsHasKnockoutIn FieldsHasKnockoutIn} (original type "fields_HasKnockoutIn"), type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsHasKnockoutIn, FieldsStrain>> getRelationshipKnockedOutIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsHasKnockoutIn, FieldsStrain>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsHasKnockoutIn, FieldsStrain>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsHasKnockoutIn, FieldsStrain>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_KnockedOutIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasMeasurement</p>
     * <pre>
     * The HasMeasurement relationship specifies a measurement(s)
     * performed on a particular experimental unit.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsHasMeasurement FieldsHasMeasurement} (original type "fields_HasMeasurement"), type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperimentalUnit, FieldsHasMeasurement, FieldsMeasurement>> getRelationshipHasMeasurement(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsHasMeasurement, FieldsMeasurement>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsHasMeasurement, FieldsMeasurement>>>>() {};
        List<List<Tuple3<FieldsExperimentalUnit, FieldsHasMeasurement, FieldsMeasurement>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasMeasurement", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsMeasureOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement"), type {@link us.kbase.cdmientityapi.FieldsHasMeasurement FieldsHasMeasurement} (original type "fields_HasMeasurement"), type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsMeasurement, FieldsHasMeasurement, FieldsExperimentalUnit>> getRelationshipIsMeasureOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsHasMeasurement, FieldsExperimentalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsHasMeasurement, FieldsExperimentalUnit>>>>() {};
        List<List<Tuple3<FieldsMeasurement, FieldsHasMeasurement, FieldsExperimentalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsMeasureOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasMember</p>
     * <pre>
     * This relationship connects each feature family to its
     * constituent features. A family always has many features, and a
     * single feature can be found in many families.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family"), type {@link us.kbase.cdmientityapi.FieldsHasMember FieldsHasMember} (original type "fields_HasMember"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFamily, FieldsHasMember, FieldsFeature>> getRelationshipHasMember(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFamily, FieldsHasMember, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsFamily, FieldsHasMember, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsFamily, FieldsHasMember, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasMember", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsMemberOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsHasMember FieldsHasMember} (original type "fields_HasMember"), type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsHasMember, FieldsFamily>> getRelationshipIsMemberOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsHasMember, FieldsFamily>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsHasMember, FieldsFamily>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsHasMember, FieldsFamily>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsMemberOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasParameter</p>
     * <pre>
     * This relationship denotes which parameters each environment has,
     * as well as the value of the parameter.
     * It has the following fields:
     * =over 4
     * =item value
     * The value of the parameter.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsEnvironment FieldsEnvironment} (original type "fields_Environment"), type {@link us.kbase.cdmientityapi.FieldsHasParameter FieldsHasParameter} (original type "fields_HasParameter"), type {@link us.kbase.cdmientityapi.FieldsParameter FieldsParameter} (original type "fields_Parameter")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsEnvironment, FieldsHasParameter, FieldsParameter>> getRelationshipHasParameter(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsEnvironment, FieldsHasParameter, FieldsParameter>>>> retType = new TypeReference<List<List<Tuple3<FieldsEnvironment, FieldsHasParameter, FieldsParameter>>>>() {};
        List<List<Tuple3<FieldsEnvironment, FieldsHasParameter, FieldsParameter>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasParameter", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_OfEnvironment</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsParameter FieldsParameter} (original type "fields_Parameter"), type {@link us.kbase.cdmientityapi.FieldsHasParameter FieldsHasParameter} (original type "fields_HasParameter"), type {@link us.kbase.cdmientityapi.FieldsEnvironment FieldsEnvironment} (original type "fields_Environment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsParameter, FieldsHasParameter, FieldsEnvironment>> getRelationshipOfEnvironment(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsParameter, FieldsHasParameter, FieldsEnvironment>>>> retType = new TypeReference<List<List<Tuple3<FieldsParameter, FieldsHasParameter, FieldsEnvironment>>>>() {};
        List<List<Tuple3<FieldsParameter, FieldsHasParameter, FieldsEnvironment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_OfEnvironment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasParticipant</p>
     * <pre>
     * A scenario consists of many participant reactions that
     * convert the input compounds to output compounds. A single reaction
     * may participate in many scenarios.
     * It has the following fields:
     * =over 4
     * =item type
     * Indicates the type of participaton. If 0, the reaction is in the main pathway of the scenario. If 1, the reaction is necessary to make the model work but is not in the subsystem. If 2, the reaction is part of the subsystem but should not be included in the modelling process.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsScenario FieldsScenario} (original type "fields_Scenario"), type {@link us.kbase.cdmientityapi.FieldsHasParticipant FieldsHasParticipant} (original type "fields_HasParticipant"), type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsScenario, FieldsHasParticipant, FieldsReaction>> getRelationshipHasParticipant(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsScenario, FieldsHasParticipant, FieldsReaction>>>> retType = new TypeReference<List<List<Tuple3<FieldsScenario, FieldsHasParticipant, FieldsReaction>>>>() {};
        List<List<Tuple3<FieldsScenario, FieldsHasParticipant, FieldsReaction>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasParticipant", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ParticipatesIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction"), type {@link us.kbase.cdmientityapi.FieldsHasParticipant FieldsHasParticipant} (original type "fields_HasParticipant"), type {@link us.kbase.cdmientityapi.FieldsScenario FieldsScenario} (original type "fields_Scenario")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsReaction, FieldsHasParticipant, FieldsScenario>> getRelationshipParticipatesIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsReaction, FieldsHasParticipant, FieldsScenario>>>> retType = new TypeReference<List<List<Tuple3<FieldsReaction, FieldsHasParticipant, FieldsScenario>>>>() {};
        List<List<Tuple3<FieldsReaction, FieldsHasParticipant, FieldsScenario>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ParticipatesIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasPresenceOf</p>
     * <pre>
     * This relationship connects a media to the compounds that
     * occur in it. The intersection data describes how much of each
     * compound can be found.
     * It has the following fields:
     * =over 4
     * =item concentration
     * The concentration of the compound in the media. A null value indicates that although the compound is present in the media, its concentration is not specified. This is typically the case for model medias which do not have physical analogs.
     * =item minimum_flux
     * minimum flux level for the compound in the medium.
     * =item maximum_flux
     * maximum flux level for the compound in the medium.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsMedia FieldsMedia} (original type "fields_Media"), type {@link us.kbase.cdmientityapi.FieldsHasPresenceOf FieldsHasPresenceOf} (original type "fields_HasPresenceOf"), type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsMedia, FieldsHasPresenceOf, FieldsCompound>> getRelationshipHasPresenceOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsMedia, FieldsHasPresenceOf, FieldsCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsMedia, FieldsHasPresenceOf, FieldsCompound>>>>() {};
        List<List<Tuple3<FieldsMedia, FieldsHasPresenceOf, FieldsCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasPresenceOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsPresentIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound"), type {@link us.kbase.cdmientityapi.FieldsHasPresenceOf FieldsHasPresenceOf} (original type "fields_HasPresenceOf"), type {@link us.kbase.cdmientityapi.FieldsMedia FieldsMedia} (original type "fields_Media")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompound, FieldsHasPresenceOf, FieldsMedia>> getRelationshipIsPresentIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompound, FieldsHasPresenceOf, FieldsMedia>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompound, FieldsHasPresenceOf, FieldsMedia>>>>() {};
        List<List<Tuple3<FieldsCompound, FieldsHasPresenceOf, FieldsMedia>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsPresentIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasProteinMember</p>
     * <pre>
     * This relationship connects each feature family to its
     * constituent protein sequences. A family always has many protein sequences,
     * and a single sequence can be found in many families.
     * It has the following fields:
     * =over 4
     * =item source_id
     * Native identifier used for the protein in the definition of the family. This will be its ID in the alignment, if one exists.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family"), type {@link us.kbase.cdmientityapi.FieldsHasProteinMember FieldsHasProteinMember} (original type "fields_HasProteinMember"), type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFamily, FieldsHasProteinMember, FieldsProteinSequence>> getRelationshipHasProteinMember(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFamily, FieldsHasProteinMember, FieldsProteinSequence>>>> retType = new TypeReference<List<List<Tuple3<FieldsFamily, FieldsHasProteinMember, FieldsProteinSequence>>>>() {};
        List<List<Tuple3<FieldsFamily, FieldsHasProteinMember, FieldsProteinSequence>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasProteinMember", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsProteinMemberOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence"), type {@link us.kbase.cdmientityapi.FieldsHasProteinMember FieldsHasProteinMember} (original type "fields_HasProteinMember"), type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProteinSequence, FieldsHasProteinMember, FieldsFamily>> getRelationshipIsProteinMemberOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsHasProteinMember, FieldsFamily>>>> retType = new TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsHasProteinMember, FieldsFamily>>>>() {};
        List<List<Tuple3<FieldsProteinSequence, FieldsHasProteinMember, FieldsFamily>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsProteinMemberOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasReactionAliasFrom</p>
     * <pre>
     * This relationship connects a source (database or organization)
     * with the reactions for which it has assigned names (aliases).
     * The alias itself is stored as intersection data.
     * It has the following fields:
     * =over 4
     * =item alias
     * alias for the reaction assigned by the source
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source"), type {@link us.kbase.cdmientityapi.FieldsHasReactionAliasFrom FieldsHasReactionAliasFrom} (original type "fields_HasReactionAliasFrom"), type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSource, FieldsHasReactionAliasFrom, FieldsReaction>> getRelationshipHasReactionAliasFrom(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSource, FieldsHasReactionAliasFrom, FieldsReaction>>>> retType = new TypeReference<List<List<Tuple3<FieldsSource, FieldsHasReactionAliasFrom, FieldsReaction>>>>() {};
        List<List<Tuple3<FieldsSource, FieldsHasReactionAliasFrom, FieldsReaction>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasReactionAliasFrom", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_UsesAliasForReaction</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction"), type {@link us.kbase.cdmientityapi.FieldsHasReactionAliasFrom FieldsHasReactionAliasFrom} (original type "fields_HasReactionAliasFrom"), type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsReaction, FieldsHasReactionAliasFrom, FieldsSource>> getRelationshipUsesAliasForReaction(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsReaction, FieldsHasReactionAliasFrom, FieldsSource>>>> retType = new TypeReference<List<List<Tuple3<FieldsReaction, FieldsHasReactionAliasFrom, FieldsSource>>>>() {};
        List<List<Tuple3<FieldsReaction, FieldsHasReactionAliasFrom, FieldsSource>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_UsesAliasForReaction", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasRegulogs</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulogCollection FieldsRegulogCollection} (original type "fields_RegulogCollection"), type {@link us.kbase.cdmientityapi.FieldsHasRegulogs FieldsHasRegulogs} (original type "fields_HasRegulogs"), type {@link us.kbase.cdmientityapi.FieldsRegulog FieldsRegulog} (original type "fields_Regulog")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulogCollection, FieldsHasRegulogs, FieldsRegulog>> getRelationshipHasRegulogs(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulogCollection, FieldsHasRegulogs, FieldsRegulog>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulogCollection, FieldsHasRegulogs, FieldsRegulog>>>>() {};
        List<List<Tuple3<FieldsRegulogCollection, FieldsHasRegulogs, FieldsRegulog>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasRegulogs", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsInCollection</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulog FieldsRegulog} (original type "fields_Regulog"), type {@link us.kbase.cdmientityapi.FieldsHasRegulogs FieldsHasRegulogs} (original type "fields_HasRegulogs"), type {@link us.kbase.cdmientityapi.FieldsRegulogCollection FieldsRegulogCollection} (original type "fields_RegulogCollection")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulog, FieldsHasRegulogs, FieldsRegulogCollection>> getRelationshipIsInCollection(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulog, FieldsHasRegulogs, FieldsRegulogCollection>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulog, FieldsHasRegulogs, FieldsRegulogCollection>>>>() {};
        List<List<Tuple3<FieldsRegulog, FieldsHasRegulogs, FieldsRegulogCollection>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsInCollection", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasRepresentativeOf</p>
     * <pre>
     * This relationship connects a genome to the FIGfam protein families
     * for which it has representative proteins. This information can be computed
     * from other relationships, but it is provided explicitly to allow fast access
     * to a genome's FIGfam profile.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsHasRepresentativeOf FieldsHasRepresentativeOf} (original type "fields_HasRepresentativeOf"), type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsHasRepresentativeOf, FieldsFamily>> getRelationshipHasRepresentativeOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsHasRepresentativeOf, FieldsFamily>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsHasRepresentativeOf, FieldsFamily>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsHasRepresentativeOf, FieldsFamily>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasRepresentativeOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRepresentedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family"), type {@link us.kbase.cdmientityapi.FieldsHasRepresentativeOf FieldsHasRepresentativeOf} (original type "fields_HasRepresentativeOf"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFamily, FieldsHasRepresentativeOf, FieldsGenome>> getRelationshipIsRepresentedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFamily, FieldsHasRepresentativeOf, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsFamily, FieldsHasRepresentativeOf, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsFamily, FieldsHasRepresentativeOf, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRepresentedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasRequirementOf</p>
     * <pre>
     * This relationship connects a model to the instances of
     * reactions that represent how the reactions occur in the model.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsModel FieldsModel} (original type "fields_Model"), type {@link us.kbase.cdmientityapi.FieldsHasRequirementOf FieldsHasRequirementOf} (original type "fields_HasRequirementOf"), type {@link us.kbase.cdmientityapi.FieldsReactionInstance FieldsReactionInstance} (original type "fields_ReactionInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsModel, FieldsHasRequirementOf, FieldsReactionInstance>> getRelationshipHasRequirementOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsModel, FieldsHasRequirementOf, FieldsReactionInstance>>>> retType = new TypeReference<List<List<Tuple3<FieldsModel, FieldsHasRequirementOf, FieldsReactionInstance>>>>() {};
        List<List<Tuple3<FieldsModel, FieldsHasRequirementOf, FieldsReactionInstance>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasRequirementOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsARequirementOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsReactionInstance FieldsReactionInstance} (original type "fields_ReactionInstance"), type {@link us.kbase.cdmientityapi.FieldsHasRequirementOf FieldsHasRequirementOf} (original type "fields_HasRequirementOf"), type {@link us.kbase.cdmientityapi.FieldsModel FieldsModel} (original type "fields_Model")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsReactionInstance, FieldsHasRequirementOf, FieldsModel>> getRelationshipIsARequirementOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsReactionInstance, FieldsHasRequirementOf, FieldsModel>>>> retType = new TypeReference<List<List<Tuple3<FieldsReactionInstance, FieldsHasRequirementOf, FieldsModel>>>>() {};
        List<List<Tuple3<FieldsReactionInstance, FieldsHasRequirementOf, FieldsModel>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsARequirementOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasResultsIn</p>
     * <pre>
     * This relationship connects a probe set to the experiments that were
     * applied to it.
     * It has the following fields:
     * =over 4
     * =item sequence
     * Sequence number of this experiment in the various result vectors.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProbeSet FieldsProbeSet} (original type "fields_ProbeSet"), type {@link us.kbase.cdmientityapi.FieldsHasResultsIn FieldsHasResultsIn} (original type "fields_HasResultsIn"), type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProbeSet, FieldsHasResultsIn, FieldsExperiment>> getRelationshipHasResultsIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProbeSet, FieldsHasResultsIn, FieldsExperiment>>>> retType = new TypeReference<List<List<Tuple3<FieldsProbeSet, FieldsHasResultsIn, FieldsExperiment>>>>() {};
        List<List<Tuple3<FieldsProbeSet, FieldsHasResultsIn, FieldsExperiment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasResultsIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasResultsFor</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment"), type {@link us.kbase.cdmientityapi.FieldsHasResultsIn FieldsHasResultsIn} (original type "fields_HasResultsIn"), type {@link us.kbase.cdmientityapi.FieldsProbeSet FieldsProbeSet} (original type "fields_ProbeSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperiment, FieldsHasResultsIn, FieldsProbeSet>> getRelationshipHasResultsFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperiment, FieldsHasResultsIn, FieldsProbeSet>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperiment, FieldsHasResultsIn, FieldsProbeSet>>>>() {};
        List<List<Tuple3<FieldsExperiment, FieldsHasResultsIn, FieldsProbeSet>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasResultsFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasSection</p>
     * <pre>
     * This relationship connects a contig's sequence to its DNA
     * sequences.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsContigSequence FieldsContigSequence} (original type "fields_ContigSequence"), type {@link us.kbase.cdmientityapi.FieldsHasSection FieldsHasSection} (original type "fields_HasSection"), type {@link us.kbase.cdmientityapi.FieldsContigChunk FieldsContigChunk} (original type "fields_ContigChunk")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsContigSequence, FieldsHasSection, FieldsContigChunk>> getRelationshipHasSection(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsContigSequence, FieldsHasSection, FieldsContigChunk>>>> retType = new TypeReference<List<List<Tuple3<FieldsContigSequence, FieldsHasSection, FieldsContigChunk>>>>() {};
        List<List<Tuple3<FieldsContigSequence, FieldsHasSection, FieldsContigChunk>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasSection", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsSectionOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsContigChunk FieldsContigChunk} (original type "fields_ContigChunk"), type {@link us.kbase.cdmientityapi.FieldsHasSection FieldsHasSection} (original type "fields_HasSection"), type {@link us.kbase.cdmientityapi.FieldsContigSequence FieldsContigSequence} (original type "fields_ContigSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsContigChunk, FieldsHasSection, FieldsContigSequence>> getRelationshipIsSectionOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsContigChunk, FieldsHasSection, FieldsContigSequence>>>> retType = new TypeReference<List<List<Tuple3<FieldsContigChunk, FieldsHasSection, FieldsContigSequence>>>>() {};
        List<List<Tuple3<FieldsContigChunk, FieldsHasSection, FieldsContigSequence>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsSectionOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasStep</p>
     * <pre>
     * This relationship connects a complex to the reactions it
     * catalyzes.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsComplex FieldsComplex} (original type "fields_Complex"), type {@link us.kbase.cdmientityapi.FieldsHasStep FieldsHasStep} (original type "fields_HasStep"), type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsComplex, FieldsHasStep, FieldsReaction>> getRelationshipHasStep(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsComplex, FieldsHasStep, FieldsReaction>>>> retType = new TypeReference<List<List<Tuple3<FieldsComplex, FieldsHasStep, FieldsReaction>>>>() {};
        List<List<Tuple3<FieldsComplex, FieldsHasStep, FieldsReaction>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasStep", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsStepOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction"), type {@link us.kbase.cdmientityapi.FieldsHasStep FieldsHasStep} (original type "fields_HasStep"), type {@link us.kbase.cdmientityapi.FieldsComplex FieldsComplex} (original type "fields_Complex")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsReaction, FieldsHasStep, FieldsComplex>> getRelationshipIsStepOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsReaction, FieldsHasStep, FieldsComplex>>>> retType = new TypeReference<List<List<Tuple3<FieldsReaction, FieldsHasStep, FieldsComplex>>>>() {};
        List<List<Tuple3<FieldsReaction, FieldsHasStep, FieldsComplex>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsStepOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasTrait</p>
     * <pre>
     * This relationship contains the measurement values of a trait on a specific observational Unit
     * It has the following fields:
     * =over 4
     * =item value
     * value of the trait measurement
     * =item statistic_type
     * text description of the statistic type (e.g. mean, median)
     * =item measure_id
     * internal ID given to this measurement
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit"), type {@link us.kbase.cdmientityapi.FieldsHasTrait FieldsHasTrait} (original type "fields_HasTrait"), type {@link us.kbase.cdmientityapi.FieldsTrait FieldsTrait} (original type "fields_Trait")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsObservationalUnit, FieldsHasTrait, FieldsTrait>> getRelationshipHasTrait(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsHasTrait, FieldsTrait>>>> retType = new TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsHasTrait, FieldsTrait>>>>() {};
        List<List<Tuple3<FieldsObservationalUnit, FieldsHasTrait, FieldsTrait>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasTrait", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Measures</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTrait FieldsTrait} (original type "fields_Trait"), type {@link us.kbase.cdmientityapi.FieldsHasTrait FieldsHasTrait} (original type "fields_HasTrait"), type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTrait, FieldsHasTrait, FieldsObservationalUnit>> getRelationshipMeasures(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTrait, FieldsHasTrait, FieldsObservationalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsTrait, FieldsHasTrait, FieldsObservationalUnit>>>>() {};
        List<List<Tuple3<FieldsTrait, FieldsHasTrait, FieldsObservationalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Measures", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasUnits</p>
     * <pre>
     * This relationship associates observational units with the
     * geographic location where the unit is planted.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsLocality FieldsLocality} (original type "fields_Locality"), type {@link us.kbase.cdmientityapi.FieldsHasUnits FieldsHasUnits} (original type "fields_HasUnits"), type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsLocality, FieldsHasUnits, FieldsObservationalUnit>> getRelationshipHasUnits(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsLocality, FieldsHasUnits, FieldsObservationalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsLocality, FieldsHasUnits, FieldsObservationalUnit>>>>() {};
        List<List<Tuple3<FieldsLocality, FieldsHasUnits, FieldsObservationalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasUnits", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsLocated</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit"), type {@link us.kbase.cdmientityapi.FieldsHasUnits FieldsHasUnits} (original type "fields_HasUnits"), type {@link us.kbase.cdmientityapi.FieldsLocality FieldsLocality} (original type "fields_Locality")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsObservationalUnit, FieldsHasUnits, FieldsLocality>> getRelationshipIsLocated(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsHasUnits, FieldsLocality>>>> retType = new TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsHasUnits, FieldsLocality>>>>() {};
        List<List<Tuple3<FieldsObservationalUnit, FieldsHasUnits, FieldsLocality>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsLocated", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasUsage</p>
     * <pre>
     * This relationship connects a specific compound in a model to the localized
     * compound to which it corresponds.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsLocalizedCompound FieldsLocalizedCompound} (original type "fields_LocalizedCompound"), type {@link us.kbase.cdmientityapi.FieldsHasUsage FieldsHasUsage} (original type "fields_HasUsage"), type {@link us.kbase.cdmientityapi.FieldsCompoundInstance FieldsCompoundInstance} (original type "fields_CompoundInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsLocalizedCompound, FieldsHasUsage, FieldsCompoundInstance>> getRelationshipHasUsage(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsLocalizedCompound, FieldsHasUsage, FieldsCompoundInstance>>>> retType = new TypeReference<List<List<Tuple3<FieldsLocalizedCompound, FieldsHasUsage, FieldsCompoundInstance>>>>() {};
        List<List<Tuple3<FieldsLocalizedCompound, FieldsHasUsage, FieldsCompoundInstance>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasUsage", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsUsageOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompoundInstance FieldsCompoundInstance} (original type "fields_CompoundInstance"), type {@link us.kbase.cdmientityapi.FieldsHasUsage FieldsHasUsage} (original type "fields_HasUsage"), type {@link us.kbase.cdmientityapi.FieldsLocalizedCompound FieldsLocalizedCompound} (original type "fields_LocalizedCompound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompoundInstance, FieldsHasUsage, FieldsLocalizedCompound>> getRelationshipIsUsageOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompoundInstance, FieldsHasUsage, FieldsLocalizedCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompoundInstance, FieldsHasUsage, FieldsLocalizedCompound>>>>() {};
        List<List<Tuple3<FieldsCompoundInstance, FieldsHasUsage, FieldsLocalizedCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsUsageOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasValueFor</p>
     * <pre>
     * This relationship connects an experiment to its attributes. The attribute
     * values are stored here.
     * It has the following fields:
     * =over 4
     * =item value
     * Value of this attribute in the given experiment. This is always encoded as a string, but may in fact be a number.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment"), type {@link us.kbase.cdmientityapi.FieldsHasValueFor FieldsHasValueFor} (original type "fields_HasValueFor"), type {@link us.kbase.cdmientityapi.FieldsAttribute FieldsAttribute} (original type "fields_Attribute")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperiment, FieldsHasValueFor, FieldsAttribute>> getRelationshipHasValueFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperiment, FieldsHasValueFor, FieldsAttribute>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperiment, FieldsHasValueFor, FieldsAttribute>>>>() {};
        List<List<Tuple3<FieldsExperiment, FieldsHasValueFor, FieldsAttribute>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasValueFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasValueIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAttribute FieldsAttribute} (original type "fields_Attribute"), type {@link us.kbase.cdmientityapi.FieldsHasValueFor FieldsHasValueFor} (original type "fields_HasValueFor"), type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAttribute, FieldsHasValueFor, FieldsExperiment>> getRelationshipHasValueIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAttribute, FieldsHasValueFor, FieldsExperiment>>>> retType = new TypeReference<List<List<Tuple3<FieldsAttribute, FieldsHasValueFor, FieldsExperiment>>>>() {};
        List<List<Tuple3<FieldsAttribute, FieldsHasValueFor, FieldsExperiment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasValueIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasVariationIn</p>
     * <pre>
     * This relationship defines an observational unit's DNA variation
     * from a contig in the reference genome.
     * It has the following fields:
     * =over 4
     * =item position
     * Position of this variation in the reference contig.
     * =item len
     * Length of the variation in the reference contig. A length of zero indicates an insertion.
     * =item data
     * Replacement DNA for the variation on the primary chromosome. An empty string indicates a deletion. The primary chromosome is chosen arbitrarily among the two chromosomes of a plant's chromosome pair (one coming from the mother and one from the father).
     * =item data2
     * Replacement DNA for the variation on the secondary chromosome. This will frequently be the same as the primary chromosome string.
     * =item quality
     * Quality score assigned to this variation.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig"), type {@link us.kbase.cdmientityapi.FieldsHasVariationIn FieldsHasVariationIn} (original type "fields_HasVariationIn"), type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsContig, FieldsHasVariationIn, FieldsObservationalUnit>> getRelationshipHasVariationIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsContig, FieldsHasVariationIn, FieldsObservationalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsContig, FieldsHasVariationIn, FieldsObservationalUnit>>>>() {};
        List<List<Tuple3<FieldsContig, FieldsHasVariationIn, FieldsObservationalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasVariationIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsVariedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit"), type {@link us.kbase.cdmientityapi.FieldsHasVariationIn FieldsHasVariationIn} (original type "fields_HasVariationIn"), type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsObservationalUnit, FieldsHasVariationIn, FieldsContig>> getRelationshipIsVariedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsHasVariationIn, FieldsContig>>>> retType = new TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsHasVariationIn, FieldsContig>>>>() {};
        List<List<Tuple3<FieldsObservationalUnit, FieldsHasVariationIn, FieldsContig>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsVariedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Impacts</p>
     * <pre>
     * This relationship contains the best scoring statistical correlations between measured traits and the responsible alleles.
     * It has the following fields:
     * =over 4
     * =item source_name
     * Name of the study which analyzed the data and determined that a variation has impact on a trait
     * =item rank
     * Rank of the position among all positions correlated with this trait.
     * =item pvalue
     * P-value of the correlation between the variation and the trait
     * =item position
     * Position in the reference contig where the trait has an impact.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTrait FieldsTrait} (original type "fields_Trait"), type {@link us.kbase.cdmientityapi.FieldsImpacts FieldsImpacts} (original type "fields_Impacts"), type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTrait, FieldsImpacts, FieldsContig>> getRelationshipImpacts(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTrait, FieldsImpacts, FieldsContig>>>> retType = new TypeReference<List<List<Tuple3<FieldsTrait, FieldsImpacts, FieldsContig>>>>() {};
        List<List<Tuple3<FieldsTrait, FieldsImpacts, FieldsContig>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Impacts", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsImpactedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig"), type {@link us.kbase.cdmientityapi.FieldsImpacts FieldsImpacts} (original type "fields_Impacts"), type {@link us.kbase.cdmientityapi.FieldsTrait FieldsTrait} (original type "fields_Trait")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsContig, FieldsImpacts, FieldsTrait>> getRelationshipIsImpactedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsContig, FieldsImpacts, FieldsTrait>>>> retType = new TypeReference<List<List<Tuple3<FieldsContig, FieldsImpacts, FieldsTrait>>>>() {};
        List<List<Tuple3<FieldsContig, FieldsImpacts, FieldsTrait>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsImpactedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ImplementsReaction</p>
     * <pre>
     * This relationship connects features to reaction instances
     * that exist because the feature is included in a model.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsImplementsReaction FieldsImplementsReaction} (original type "fields_ImplementsReaction"), type {@link us.kbase.cdmientityapi.FieldsReactionInstance FieldsReactionInstance} (original type "fields_ReactionInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsImplementsReaction, FieldsReactionInstance>> getRelationshipImplementsReaction(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsImplementsReaction, FieldsReactionInstance>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsImplementsReaction, FieldsReactionInstance>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsImplementsReaction, FieldsReactionInstance>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ImplementsReaction", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ImplementedBasedOn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsReactionInstance FieldsReactionInstance} (original type "fields_ReactionInstance"), type {@link us.kbase.cdmientityapi.FieldsImplementsReaction FieldsImplementsReaction} (original type "fields_ImplementsReaction"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsReactionInstance, FieldsImplementsReaction, FieldsFeature>> getRelationshipImplementedBasedOn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsReactionInstance, FieldsImplementsReaction, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsReactionInstance, FieldsImplementsReaction, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsReactionInstance, FieldsImplementsReaction, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ImplementedBasedOn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Includes</p>
     * <pre>
     * A subsystem is defined by its roles. The subsystem's variants
     * contain slightly different sets of roles, but all of the roles in a
     * variant must be connected to the parent subsystem by this
     * relationship. A subsystem always has at least one role, and a role
     * always belongs to at least one subsystem.
     * It has the following fields:
     * =over 4
     * =item sequence
     * Sequence number of the role within the subsystem. When the roles are formed into a variant, they will generally appear in sequence order.
     * =item abbreviation
     * Abbreviation for this role in this subsystem. The abbreviations are used in columnar displays, and they also appear on diagrams.
     * =item auxiliary
     * TRUE if this is an auxiliary role, or FALSE if this role is a functioning part of the subsystem.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem"), type {@link us.kbase.cdmientityapi.FieldsIncludes FieldsIncludes} (original type "fields_Includes"), type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSubsystem, FieldsIncludes, FieldsRole>> getRelationshipIncludes(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsIncludes, FieldsRole>>>> retType = new TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsIncludes, FieldsRole>>>>() {};
        List<List<Tuple3<FieldsSubsystem, FieldsIncludes, FieldsRole>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Includes", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsIncludedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role"), type {@link us.kbase.cdmientityapi.FieldsIncludes FieldsIncludes} (original type "fields_Includes"), type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRole, FieldsIncludes, FieldsSubsystem>> getRelationshipIsIncludedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRole, FieldsIncludes, FieldsSubsystem>>>> retType = new TypeReference<List<List<Tuple3<FieldsRole, FieldsIncludes, FieldsSubsystem>>>>() {};
        List<List<Tuple3<FieldsRole, FieldsIncludes, FieldsSubsystem>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsIncludedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IncludesAdditionalCompounds</p>
     * <pre>
     * This relationship connects a environment to the compounds that
     * occur in it. The intersection data describes how much of each
     * compound can be found.
     * It has the following fields:
     * =over 4
     * =item concentration
     * The concentration of the compound in the environment. A null value indicates that although the compound is present in the environment, its concentration is not specified. This is typically the case for model environments which do not have physical analogs.
     * =item units
     * vol%, g/L, or molar (mol/L).
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsEnvironment FieldsEnvironment} (original type "fields_Environment"), type {@link us.kbase.cdmientityapi.FieldsIncludesAdditionalCompounds FieldsIncludesAdditionalCompounds} (original type "fields_IncludesAdditionalCompounds"), type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsEnvironment, FieldsIncludesAdditionalCompounds, FieldsCompound>> getRelationshipIncludesAdditionalCompounds(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsEnvironment, FieldsIncludesAdditionalCompounds, FieldsCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsEnvironment, FieldsIncludesAdditionalCompounds, FieldsCompound>>>>() {};
        List<List<Tuple3<FieldsEnvironment, FieldsIncludesAdditionalCompounds, FieldsCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IncludesAdditionalCompounds", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IncludedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound"), type {@link us.kbase.cdmientityapi.FieldsIncludesAdditionalCompounds FieldsIncludesAdditionalCompounds} (original type "fields_IncludesAdditionalCompounds"), type {@link us.kbase.cdmientityapi.FieldsEnvironment FieldsEnvironment} (original type "fields_Environment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompound, FieldsIncludesAdditionalCompounds, FieldsEnvironment>> getRelationshipIncludedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompound, FieldsIncludesAdditionalCompounds, FieldsEnvironment>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompound, FieldsIncludesAdditionalCompounds, FieldsEnvironment>>>>() {};
        List<List<Tuple3<FieldsCompound, FieldsIncludesAdditionalCompounds, FieldsEnvironment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IncludedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IncludesAlignmentRow</p>
     * <pre>
     * This relationship connects an alignment to its component
     * rows.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment"), type {@link us.kbase.cdmientityapi.FieldsIncludesAlignmentRow FieldsIncludesAlignmentRow} (original type "fields_IncludesAlignmentRow"), type {@link us.kbase.cdmientityapi.FieldsAlignmentRow FieldsAlignmentRow} (original type "fields_AlignmentRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignment, FieldsIncludesAlignmentRow, FieldsAlignmentRow>> getRelationshipIncludesAlignmentRow(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignment, FieldsIncludesAlignmentRow, FieldsAlignmentRow>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignment, FieldsIncludesAlignmentRow, FieldsAlignmentRow>>>>() {};
        List<List<Tuple3<FieldsAlignment, FieldsIncludesAlignmentRow, FieldsAlignmentRow>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IncludesAlignmentRow", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsAlignmentRowIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignmentRow FieldsAlignmentRow} (original type "fields_AlignmentRow"), type {@link us.kbase.cdmientityapi.FieldsIncludesAlignmentRow FieldsIncludesAlignmentRow} (original type "fields_IncludesAlignmentRow"), type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignmentRow, FieldsIncludesAlignmentRow, FieldsAlignment>> getRelationshipIsAlignmentRowIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignmentRow, FieldsIncludesAlignmentRow, FieldsAlignment>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignmentRow, FieldsIncludesAlignmentRow, FieldsAlignment>>>>() {};
        List<List<Tuple3<FieldsAlignmentRow, FieldsIncludesAlignmentRow, FieldsAlignment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsAlignmentRowIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IncludesPart</p>
     * <pre>
     * This relationship associates observational units with the
     * experiments that generated the data on them.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsStudyExperiment FieldsStudyExperiment} (original type "fields_StudyExperiment"), type {@link us.kbase.cdmientityapi.FieldsIncludesPart FieldsIncludesPart} (original type "fields_IncludesPart"), type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsStudyExperiment, FieldsIncludesPart, FieldsObservationalUnit>> getRelationshipIncludesPart(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsStudyExperiment, FieldsIncludesPart, FieldsObservationalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsStudyExperiment, FieldsIncludesPart, FieldsObservationalUnit>>>>() {};
        List<List<Tuple3<FieldsStudyExperiment, FieldsIncludesPart, FieldsObservationalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IncludesPart", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsPartOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit"), type {@link us.kbase.cdmientityapi.FieldsIncludesPart FieldsIncludesPart} (original type "fields_IncludesPart"), type {@link us.kbase.cdmientityapi.FieldsStudyExperiment FieldsStudyExperiment} (original type "fields_StudyExperiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsObservationalUnit, FieldsIncludesPart, FieldsStudyExperiment>> getRelationshipIsPartOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsIncludesPart, FieldsStudyExperiment>>>> retType = new TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsIncludesPart, FieldsStudyExperiment>>>>() {};
        List<List<Tuple3<FieldsObservationalUnit, FieldsIncludesPart, FieldsStudyExperiment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsPartOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IndicatedLevelsFor</p>
     * <pre>
     * This relationship connects a feature to a probe set from which experimental
     * data was produced for the feature. It contains a vector of the expression levels.
     * It has the following fields:
     * =over 4
     * =item level_vector
     * Vector of expression levels (-1, 0, 1) for the experiments, in sequence order.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProbeSet FieldsProbeSet} (original type "fields_ProbeSet"), type {@link us.kbase.cdmientityapi.FieldsIndicatedLevelsFor FieldsIndicatedLevelsFor} (original type "fields_IndicatedLevelsFor"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProbeSet, FieldsIndicatedLevelsFor, FieldsFeature>> getRelationshipIndicatedLevelsFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProbeSet, FieldsIndicatedLevelsFor, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsProbeSet, FieldsIndicatedLevelsFor, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsProbeSet, FieldsIndicatedLevelsFor, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IndicatedLevelsFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasLevelsFrom</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIndicatedLevelsFor FieldsIndicatedLevelsFor} (original type "fields_IndicatedLevelsFor"), type {@link us.kbase.cdmientityapi.FieldsProbeSet FieldsProbeSet} (original type "fields_ProbeSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIndicatedLevelsFor, FieldsProbeSet>> getRelationshipHasLevelsFrom(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIndicatedLevelsFor, FieldsProbeSet>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIndicatedLevelsFor, FieldsProbeSet>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIndicatedLevelsFor, FieldsProbeSet>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasLevelsFrom", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Involves</p>
     * <pre>
     * This relationship connects a reaction to the
     * specific localized compounds that participate in it.
     * It has the following fields:
     * =over 4
     * =item coefficient
     * Number of molecules of the compound that participate in a single instance of the reaction. For example, if a reaction produces two water molecules, the stoichiometry of water for the reaction would be two. When a reaction is written on paper in chemical notation, the stoichiometry is the number next to the chemical formula of the compound. The value is negative for substrates and positive for products.
     * =item cofactor
     * TRUE if the compound is a cofactor; FALSE if it is a major component of the reaction.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction"), type {@link us.kbase.cdmientityapi.FieldsInvolves FieldsInvolves} (original type "fields_Involves"), type {@link us.kbase.cdmientityapi.FieldsLocalizedCompound FieldsLocalizedCompound} (original type "fields_LocalizedCompound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsReaction, FieldsInvolves, FieldsLocalizedCompound>> getRelationshipInvolves(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsReaction, FieldsInvolves, FieldsLocalizedCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsReaction, FieldsInvolves, FieldsLocalizedCompound>>>>() {};
        List<List<Tuple3<FieldsReaction, FieldsInvolves, FieldsLocalizedCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Involves", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsInvolvedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsLocalizedCompound FieldsLocalizedCompound} (original type "fields_LocalizedCompound"), type {@link us.kbase.cdmientityapi.FieldsInvolves FieldsInvolves} (original type "fields_Involves"), type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsLocalizedCompound, FieldsInvolves, FieldsReaction>> getRelationshipIsInvolvedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsLocalizedCompound, FieldsInvolves, FieldsReaction>>>> retType = new TypeReference<List<List<Tuple3<FieldsLocalizedCompound, FieldsInvolves, FieldsReaction>>>>() {};
        List<List<Tuple3<FieldsLocalizedCompound, FieldsInvolves, FieldsReaction>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsInvolvedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsAnnotatedBy</p>
     * <pre>
     * This relationship connects a feature to its annotations. A
     * feature may have multiple annotations, but an annotation belongs to
     * only one feature.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsAnnotatedBy FieldsIsAnnotatedBy} (original type "fields_IsAnnotatedBy"), type {@link us.kbase.cdmientityapi.FieldsAnnotation FieldsAnnotation} (original type "fields_Annotation")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsAnnotatedBy, FieldsAnnotation>> getRelationshipIsAnnotatedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsAnnotatedBy, FieldsAnnotation>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsAnnotatedBy, FieldsAnnotation>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsAnnotatedBy, FieldsAnnotation>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsAnnotatedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Annotates</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAnnotation FieldsAnnotation} (original type "fields_Annotation"), type {@link us.kbase.cdmientityapi.FieldsIsAnnotatedBy FieldsIsAnnotatedBy} (original type "fields_IsAnnotatedBy"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAnnotation, FieldsIsAnnotatedBy, FieldsFeature>> getRelationshipAnnotates(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAnnotation, FieldsIsAnnotatedBy, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsAnnotation, FieldsIsAnnotatedBy, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsAnnotation, FieldsIsAnnotatedBy, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Annotates", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsAssayOf</p>
     * <pre>
     * This relationship associates each assay with the relevant
     * experiments.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAssay FieldsAssay} (original type "fields_Assay"), type {@link us.kbase.cdmientityapi.FieldsIsAssayOf FieldsIsAssayOf} (original type "fields_IsAssayOf"), type {@link us.kbase.cdmientityapi.FieldsStudyExperiment FieldsStudyExperiment} (original type "fields_StudyExperiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAssay, FieldsIsAssayOf, FieldsStudyExperiment>> getRelationshipIsAssayOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAssay, FieldsIsAssayOf, FieldsStudyExperiment>>>> retType = new TypeReference<List<List<Tuple3<FieldsAssay, FieldsIsAssayOf, FieldsStudyExperiment>>>>() {};
        List<List<Tuple3<FieldsAssay, FieldsIsAssayOf, FieldsStudyExperiment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsAssayOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsAssayedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsStudyExperiment FieldsStudyExperiment} (original type "fields_StudyExperiment"), type {@link us.kbase.cdmientityapi.FieldsIsAssayOf FieldsIsAssayOf} (original type "fields_IsAssayOf"), type {@link us.kbase.cdmientityapi.FieldsAssay FieldsAssay} (original type "fields_Assay")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsStudyExperiment, FieldsIsAssayOf, FieldsAssay>> getRelationshipIsAssayedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsStudyExperiment, FieldsIsAssayOf, FieldsAssay>>>> retType = new TypeReference<List<List<Tuple3<FieldsStudyExperiment, FieldsIsAssayOf, FieldsAssay>>>>() {};
        List<List<Tuple3<FieldsStudyExperiment, FieldsIsAssayOf, FieldsAssay>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsAssayedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsClassFor</p>
     * <pre>
     * This relationship connects each subsystem class with the
     * subsystems that belong to it. A class can contain many subsystems,
     * but a subsystem is only in one class. Some subsystems are not in any
     * class, but this is usually a temporary condition.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSubsystemClass FieldsSubsystemClass} (original type "fields_SubsystemClass"), type {@link us.kbase.cdmientityapi.FieldsIsClassFor FieldsIsClassFor} (original type "fields_IsClassFor"), type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSubsystemClass, FieldsIsClassFor, FieldsSubsystem>> getRelationshipIsClassFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSubsystemClass, FieldsIsClassFor, FieldsSubsystem>>>> retType = new TypeReference<List<List<Tuple3<FieldsSubsystemClass, FieldsIsClassFor, FieldsSubsystem>>>>() {};
        List<List<Tuple3<FieldsSubsystemClass, FieldsIsClassFor, FieldsSubsystem>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsClassFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsInClass</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem"), type {@link us.kbase.cdmientityapi.FieldsIsClassFor FieldsIsClassFor} (original type "fields_IsClassFor"), type {@link us.kbase.cdmientityapi.FieldsSubsystemClass FieldsSubsystemClass} (original type "fields_SubsystemClass")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSubsystem, FieldsIsClassFor, FieldsSubsystemClass>> getRelationshipIsInClass(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsIsClassFor, FieldsSubsystemClass>>>> retType = new TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsIsClassFor, FieldsSubsystemClass>>>>() {};
        List<List<Tuple3<FieldsSubsystem, FieldsIsClassFor, FieldsSubsystemClass>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsInClass", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsCollectionOf</p>
     * <pre>
     * A genome belongs to only one genome set. For each set, this relationship marks the genome to be used as its representative.
     * It has the following fields:
     * =over 4
     * =item representative
     * TRUE for the representative genome of the set, else FALSE.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsOTU FieldsOTU} (original type "fields_OTU"), type {@link us.kbase.cdmientityapi.FieldsIsCollectionOf FieldsIsCollectionOf} (original type "fields_IsCollectionOf"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsOTU, FieldsIsCollectionOf, FieldsGenome>> getRelationshipIsCollectionOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsOTU, FieldsIsCollectionOf, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsOTU, FieldsIsCollectionOf, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsOTU, FieldsIsCollectionOf, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsCollectionOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsCollectedInto</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsIsCollectionOf FieldsIsCollectionOf} (original type "fields_IsCollectionOf"), type {@link us.kbase.cdmientityapi.FieldsOTU FieldsOTU} (original type "fields_OTU")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsIsCollectionOf, FieldsOTU>> getRelationshipIsCollectedInto(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsCollectionOf, FieldsOTU>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsCollectionOf, FieldsOTU>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsIsCollectionOf, FieldsOTU>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsCollectedInto", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsComposedOf</p>
     * <pre>
     * This relationship connects a genome to its
     * constituent contigs. Unlike contig sequences, a
     * contig belongs to only one genome.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsIsComposedOf FieldsIsComposedOf} (original type "fields_IsComposedOf"), type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsIsComposedOf, FieldsContig>> getRelationshipIsComposedOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsComposedOf, FieldsContig>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsComposedOf, FieldsContig>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsIsComposedOf, FieldsContig>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsComposedOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsComponentOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig"), type {@link us.kbase.cdmientityapi.FieldsIsComposedOf FieldsIsComposedOf} (original type "fields_IsComposedOf"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsContig, FieldsIsComposedOf, FieldsGenome>> getRelationshipIsComponentOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsContig, FieldsIsComposedOf, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsContig, FieldsIsComposedOf, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsContig, FieldsIsComposedOf, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsComponentOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsComprisedOf</p>
     * <pre>
     * This relationship connects a biomass composition reaction to the
     * compounds specified as contained in the biomass.
     * It has the following fields:
     * =over 4
     * =item coefficient
     * number of millimoles of the compound instance that exists in one gram cell dry weight of biomass
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsBiomass FieldsBiomass} (original type "fields_Biomass"), type {@link us.kbase.cdmientityapi.FieldsIsComprisedOf FieldsIsComprisedOf} (original type "fields_IsComprisedOf"), type {@link us.kbase.cdmientityapi.FieldsCompoundInstance FieldsCompoundInstance} (original type "fields_CompoundInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsBiomass, FieldsIsComprisedOf, FieldsCompoundInstance>> getRelationshipIsComprisedOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsBiomass, FieldsIsComprisedOf, FieldsCompoundInstance>>>> retType = new TypeReference<List<List<Tuple3<FieldsBiomass, FieldsIsComprisedOf, FieldsCompoundInstance>>>>() {};
        List<List<Tuple3<FieldsBiomass, FieldsIsComprisedOf, FieldsCompoundInstance>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsComprisedOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Comprises</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompoundInstance FieldsCompoundInstance} (original type "fields_CompoundInstance"), type {@link us.kbase.cdmientityapi.FieldsIsComprisedOf FieldsIsComprisedOf} (original type "fields_IsComprisedOf"), type {@link us.kbase.cdmientityapi.FieldsBiomass FieldsBiomass} (original type "fields_Biomass")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompoundInstance, FieldsIsComprisedOf, FieldsBiomass>> getRelationshipComprises(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompoundInstance, FieldsIsComprisedOf, FieldsBiomass>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompoundInstance, FieldsIsComprisedOf, FieldsBiomass>>>>() {};
        List<List<Tuple3<FieldsCompoundInstance, FieldsIsComprisedOf, FieldsBiomass>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Comprises", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsConfiguredBy</p>
     * <pre>
     * This relationship connects a genome to the atomic regulons that
     * describe its state.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsIsConfiguredBy FieldsIsConfiguredBy} (original type "fields_IsConfiguredBy"), type {@link us.kbase.cdmientityapi.FieldsAtomicRegulon FieldsAtomicRegulon} (original type "fields_AtomicRegulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsIsConfiguredBy, FieldsAtomicRegulon>> getRelationshipIsConfiguredBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsConfiguredBy, FieldsAtomicRegulon>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsConfiguredBy, FieldsAtomicRegulon>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsIsConfiguredBy, FieldsAtomicRegulon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsConfiguredBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ReflectsStateOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAtomicRegulon FieldsAtomicRegulon} (original type "fields_AtomicRegulon"), type {@link us.kbase.cdmientityapi.FieldsIsConfiguredBy FieldsIsConfiguredBy} (original type "fields_IsConfiguredBy"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAtomicRegulon, FieldsIsConfiguredBy, FieldsGenome>> getRelationshipReflectsStateOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAtomicRegulon, FieldsIsConfiguredBy, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsAtomicRegulon, FieldsIsConfiguredBy, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsAtomicRegulon, FieldsIsConfiguredBy, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ReflectsStateOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsConservedDomainModelFor</p>
     * <pre>
     * This relationship connects a protein sequence with the conserved domains
     * that have been computed to be associated with it.
     * It has the following fields:
     * =over 4
     * =item percent_identity
     * =item alignment_length
     * =item mismatches
     * =item gap_openings
     * =item protein_start
     * =item protein_end
     * =item domain_start
     * =item domain_end
     * =item e_value
     * =item bit_score
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsConservedDomainModel FieldsConservedDomainModel} (original type "fields_ConservedDomainModel"), type {@link us.kbase.cdmientityapi.FieldsIsConservedDomainModelFor FieldsIsConservedDomainModelFor} (original type "fields_IsConservedDomainModelFor"), type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsConservedDomainModel, FieldsIsConservedDomainModelFor, FieldsProteinSequence>> getRelationshipIsConservedDomainModelFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsConservedDomainModel, FieldsIsConservedDomainModelFor, FieldsProteinSequence>>>> retType = new TypeReference<List<List<Tuple3<FieldsConservedDomainModel, FieldsIsConservedDomainModelFor, FieldsProteinSequence>>>>() {};
        List<List<Tuple3<FieldsConservedDomainModel, FieldsIsConservedDomainModelFor, FieldsProteinSequence>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsConservedDomainModelFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasConservedDomainModel</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence"), type {@link us.kbase.cdmientityapi.FieldsIsConservedDomainModelFor FieldsIsConservedDomainModelFor} (original type "fields_IsConservedDomainModelFor"), type {@link us.kbase.cdmientityapi.FieldsConservedDomainModel FieldsConservedDomainModel} (original type "fields_ConservedDomainModel")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProteinSequence, FieldsIsConservedDomainModelFor, FieldsConservedDomainModel>> getRelationshipHasConservedDomainModel(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsIsConservedDomainModelFor, FieldsConservedDomainModel>>>> retType = new TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsIsConservedDomainModelFor, FieldsConservedDomainModel>>>>() {};
        List<List<Tuple3<FieldsProteinSequence, FieldsIsConservedDomainModelFor, FieldsConservedDomainModel>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasConservedDomainModel", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsConsistentWith</p>
     * <pre>
     * This relationship connects a functional role to the EC numbers consistent
     * with the chemistry described in the role.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsEcNumber FieldsEcNumber} (original type "fields_EcNumber"), type {@link us.kbase.cdmientityapi.FieldsIsConsistentWith FieldsIsConsistentWith} (original type "fields_IsConsistentWith"), type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsEcNumber, FieldsIsConsistentWith, FieldsRole>> getRelationshipIsConsistentWith(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsEcNumber, FieldsIsConsistentWith, FieldsRole>>>> retType = new TypeReference<List<List<Tuple3<FieldsEcNumber, FieldsIsConsistentWith, FieldsRole>>>>() {};
        List<List<Tuple3<FieldsEcNumber, FieldsIsConsistentWith, FieldsRole>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsConsistentWith", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsConsistentTo</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role"), type {@link us.kbase.cdmientityapi.FieldsIsConsistentWith FieldsIsConsistentWith} (original type "fields_IsConsistentWith"), type {@link us.kbase.cdmientityapi.FieldsEcNumber FieldsEcNumber} (original type "fields_EcNumber")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRole, FieldsIsConsistentWith, FieldsEcNumber>> getRelationshipIsConsistentTo(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRole, FieldsIsConsistentWith, FieldsEcNumber>>>> retType = new TypeReference<List<List<Tuple3<FieldsRole, FieldsIsConsistentWith, FieldsEcNumber>>>>() {};
        List<List<Tuple3<FieldsRole, FieldsIsConsistentWith, FieldsEcNumber>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsConsistentTo", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsContextOf</p>
     * <pre>
     * The IsContextOf relationship describes the enviroment a
     * subexperiment defined by an ExperimentalUnit was performed in.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsEnvironment FieldsEnvironment} (original type "fields_Environment"), type {@link us.kbase.cdmientityapi.FieldsIsContextOf FieldsIsContextOf} (original type "fields_IsContextOf"), type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsEnvironment, FieldsIsContextOf, FieldsExperimentalUnit>> getRelationshipIsContextOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsEnvironment, FieldsIsContextOf, FieldsExperimentalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsEnvironment, FieldsIsContextOf, FieldsExperimentalUnit>>>>() {};
        List<List<Tuple3<FieldsEnvironment, FieldsIsContextOf, FieldsExperimentalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsContextOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasEnvironment</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsIsContextOf FieldsIsContextOf} (original type "fields_IsContextOf"), type {@link us.kbase.cdmientityapi.FieldsEnvironment FieldsEnvironment} (original type "fields_Environment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperimentalUnit, FieldsIsContextOf, FieldsEnvironment>> getRelationshipHasEnvironment(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsIsContextOf, FieldsEnvironment>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsIsContextOf, FieldsEnvironment>>>>() {};
        List<List<Tuple3<FieldsExperimentalUnit, FieldsIsContextOf, FieldsEnvironment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasEnvironment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsCoregulatedWith</p>
     * <pre>
     * This relationship connects a feature with another feature in the
     * same genome with which it appears to be coregulated as a result of
     * expression data analysis.
     * It has the following fields:
     * =over 4
     * =item coefficient
     * Pearson correlation coefficient for this coregulation.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsCoregulatedWith FieldsIsCoregulatedWith} (original type "fields_IsCoregulatedWith"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsCoregulatedWith, FieldsFeature>> getRelationshipIsCoregulatedWith(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsCoregulatedWith, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsCoregulatedWith, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsCoregulatedWith, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsCoregulatedWith", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasCoregulationWith</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsCoregulatedWith FieldsIsCoregulatedWith} (original type "fields_IsCoregulatedWith"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsCoregulatedWith, FieldsFeature>> getRelationshipHasCoregulationWith(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsCoregulatedWith, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsCoregulatedWith, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsCoregulatedWith, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasCoregulationWith", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsCoupledTo</p>
     * <pre>
     * This relationship connects two FIGfams that we believe to be related
     * either because their members occur in proximity on chromosomes or because
     * the members are expressed together. Such a relationship is evidence the
     * functions of the FIGfams are themselves related. This relationship is
     * commutative; only the instance in which the first FIGfam has a lower ID
     * than the second is stored.
     * It has the following fields:
     * =over 4
     * =item co_occurrence_evidence
     * number of times members of the two FIGfams occur close to each other on chromosomes
     * =item co_expression_evidence
     * number of times members of the two FIGfams are co-expressed in expression data experiments
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family"), type {@link us.kbase.cdmientityapi.FieldsIsCoupledTo FieldsIsCoupledTo} (original type "fields_IsCoupledTo"), type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFamily, FieldsIsCoupledTo, FieldsFamily>> getRelationshipIsCoupledTo(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFamily, FieldsIsCoupledTo, FieldsFamily>>>> retType = new TypeReference<List<List<Tuple3<FieldsFamily, FieldsIsCoupledTo, FieldsFamily>>>>() {};
        List<List<Tuple3<FieldsFamily, FieldsIsCoupledTo, FieldsFamily>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsCoupledTo", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsCoupledWith</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family"), type {@link us.kbase.cdmientityapi.FieldsIsCoupledTo FieldsIsCoupledTo} (original type "fields_IsCoupledTo"), type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFamily, FieldsIsCoupledTo, FieldsFamily>> getRelationshipIsCoupledWith(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFamily, FieldsIsCoupledTo, FieldsFamily>>>> retType = new TypeReference<List<List<Tuple3<FieldsFamily, FieldsIsCoupledTo, FieldsFamily>>>>() {};
        List<List<Tuple3<FieldsFamily, FieldsIsCoupledTo, FieldsFamily>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsCoupledWith", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsDatasetFor</p>
     * <pre>
     * The IsDatasetFor relationship describes which genomes
     * are covered by particular association datasets.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAssociationDataset FieldsAssociationDataset} (original type "fields_AssociationDataset"), type {@link us.kbase.cdmientityapi.FieldsIsDatasetFor FieldsIsDatasetFor} (original type "fields_IsDatasetFor"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAssociationDataset, FieldsIsDatasetFor, FieldsGenome>> getRelationshipIsDatasetFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAssociationDataset, FieldsIsDatasetFor, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsAssociationDataset, FieldsIsDatasetFor, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsAssociationDataset, FieldsIsDatasetFor, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsDatasetFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasAssociationDataset</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsIsDatasetFor FieldsIsDatasetFor} (original type "fields_IsDatasetFor"), type {@link us.kbase.cdmientityapi.FieldsAssociationDataset FieldsAssociationDataset} (original type "fields_AssociationDataset")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsIsDatasetFor, FieldsAssociationDataset>> getRelationshipHasAssociationDataset(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsDatasetFor, FieldsAssociationDataset>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsDatasetFor, FieldsAssociationDataset>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsIsDatasetFor, FieldsAssociationDataset>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasAssociationDataset", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsDeterminedBy</p>
     * <pre>
     * A functional coupling evidence set exists because it has
     * pairings in it, and this relationship connects the evidence set to
     * its constituent pairings. A pairing cam belong to multiple evidence
     * sets.
     * It has the following fields:
     * =over 4
     * =item inverted
     * A pairing is an unordered pair of protein sequences, but its similarity to other pairings in a pair set is ordered. Let (A,B) be a pairing and (X,Y) be another pairing in the same set. If this flag is FALSE, then (A =~ X) and (B =~ Y). If this flag is TRUE, then (A =~ Y) and (B =~ X).
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPairSet FieldsPairSet} (original type "fields_PairSet"), type {@link us.kbase.cdmientityapi.FieldsIsDeterminedBy FieldsIsDeterminedBy} (original type "fields_IsDeterminedBy"), type {@link us.kbase.cdmientityapi.FieldsPairing FieldsPairing} (original type "fields_Pairing")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPairSet, FieldsIsDeterminedBy, FieldsPairing>> getRelationshipIsDeterminedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPairSet, FieldsIsDeterminedBy, FieldsPairing>>>> retType = new TypeReference<List<List<Tuple3<FieldsPairSet, FieldsIsDeterminedBy, FieldsPairing>>>>() {};
        List<List<Tuple3<FieldsPairSet, FieldsIsDeterminedBy, FieldsPairing>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsDeterminedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Determines</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPairing FieldsPairing} (original type "fields_Pairing"), type {@link us.kbase.cdmientityapi.FieldsIsDeterminedBy FieldsIsDeterminedBy} (original type "fields_IsDeterminedBy"), type {@link us.kbase.cdmientityapi.FieldsPairSet FieldsPairSet} (original type "fields_PairSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPairing, FieldsIsDeterminedBy, FieldsPairSet>> getRelationshipDetermines(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPairing, FieldsIsDeterminedBy, FieldsPairSet>>>> retType = new TypeReference<List<List<Tuple3<FieldsPairing, FieldsIsDeterminedBy, FieldsPairSet>>>>() {};
        List<List<Tuple3<FieldsPairing, FieldsIsDeterminedBy, FieldsPairSet>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Determines", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsDividedInto</p>
     * <pre>
     * This relationship connects a model to its instances of
     * subcellular locations that participate in the model.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsModel FieldsModel} (original type "fields_Model"), type {@link us.kbase.cdmientityapi.FieldsIsDividedInto FieldsIsDividedInto} (original type "fields_IsDividedInto"), type {@link us.kbase.cdmientityapi.FieldsLocationInstance FieldsLocationInstance} (original type "fields_LocationInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsModel, FieldsIsDividedInto, FieldsLocationInstance>> getRelationshipIsDividedInto(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsModel, FieldsIsDividedInto, FieldsLocationInstance>>>> retType = new TypeReference<List<List<Tuple3<FieldsModel, FieldsIsDividedInto, FieldsLocationInstance>>>>() {};
        List<List<Tuple3<FieldsModel, FieldsIsDividedInto, FieldsLocationInstance>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsDividedInto", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsDivisionOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsLocationInstance FieldsLocationInstance} (original type "fields_LocationInstance"), type {@link us.kbase.cdmientityapi.FieldsIsDividedInto FieldsIsDividedInto} (original type "fields_IsDividedInto"), type {@link us.kbase.cdmientityapi.FieldsModel FieldsModel} (original type "fields_Model")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsLocationInstance, FieldsIsDividedInto, FieldsModel>> getRelationshipIsDivisionOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsLocationInstance, FieldsIsDividedInto, FieldsModel>>>> retType = new TypeReference<List<List<Tuple3<FieldsLocationInstance, FieldsIsDividedInto, FieldsModel>>>>() {};
        List<List<Tuple3<FieldsLocationInstance, FieldsIsDividedInto, FieldsModel>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsDivisionOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsExecutedAs</p>
     * <pre>
     * This relationship links a reaction to the way it is used in a model.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction"), type {@link us.kbase.cdmientityapi.FieldsIsExecutedAs FieldsIsExecutedAs} (original type "fields_IsExecutedAs"), type {@link us.kbase.cdmientityapi.FieldsReactionInstance FieldsReactionInstance} (original type "fields_ReactionInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsReaction, FieldsIsExecutedAs, FieldsReactionInstance>> getRelationshipIsExecutedAs(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsReaction, FieldsIsExecutedAs, FieldsReactionInstance>>>> retType = new TypeReference<List<List<Tuple3<FieldsReaction, FieldsIsExecutedAs, FieldsReactionInstance>>>>() {};
        List<List<Tuple3<FieldsReaction, FieldsIsExecutedAs, FieldsReactionInstance>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsExecutedAs", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsExecutionOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsReactionInstance FieldsReactionInstance} (original type "fields_ReactionInstance"), type {@link us.kbase.cdmientityapi.FieldsIsExecutedAs FieldsIsExecutedAs} (original type "fields_IsExecutedAs"), type {@link us.kbase.cdmientityapi.FieldsReaction FieldsReaction} (original type "fields_Reaction")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsReactionInstance, FieldsIsExecutedAs, FieldsReaction>> getRelationshipIsExecutionOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsReactionInstance, FieldsIsExecutedAs, FieldsReaction>>>> retType = new TypeReference<List<List<Tuple3<FieldsReactionInstance, FieldsIsExecutedAs, FieldsReaction>>>>() {};
        List<List<Tuple3<FieldsReactionInstance, FieldsIsExecutedAs, FieldsReaction>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsExecutionOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsExemplarOf</p>
     * <pre>
     * This relationship links a role to a feature that provides a typical
     * example of how the role is implemented.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsExemplarOf FieldsIsExemplarOf} (original type "fields_IsExemplarOf"), type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsExemplarOf, FieldsRole>> getRelationshipIsExemplarOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsExemplarOf, FieldsRole>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsExemplarOf, FieldsRole>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsExemplarOf, FieldsRole>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsExemplarOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasAsExemplar</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role"), type {@link us.kbase.cdmientityapi.FieldsIsExemplarOf FieldsIsExemplarOf} (original type "fields_IsExemplarOf"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRole, FieldsIsExemplarOf, FieldsFeature>> getRelationshipHasAsExemplar(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRole, FieldsIsExemplarOf, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsRole, FieldsIsExemplarOf, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsRole, FieldsIsExemplarOf, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasAsExemplar", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsFamilyFor</p>
     * <pre>
     * This relationship connects an isofunctional family to the roles that
     * make up its assigned function.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family"), type {@link us.kbase.cdmientityapi.FieldsIsFamilyFor FieldsIsFamilyFor} (original type "fields_IsFamilyFor"), type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFamily, FieldsIsFamilyFor, FieldsRole>> getRelationshipIsFamilyFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFamily, FieldsIsFamilyFor, FieldsRole>>>> retType = new TypeReference<List<List<Tuple3<FieldsFamily, FieldsIsFamilyFor, FieldsRole>>>>() {};
        List<List<Tuple3<FieldsFamily, FieldsIsFamilyFor, FieldsRole>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsFamilyFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_DeterminesFunctionOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role"), type {@link us.kbase.cdmientityapi.FieldsIsFamilyFor FieldsIsFamilyFor} (original type "fields_IsFamilyFor"), type {@link us.kbase.cdmientityapi.FieldsFamily FieldsFamily} (original type "fields_Family")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRole, FieldsIsFamilyFor, FieldsFamily>> getRelationshipDeterminesFunctionOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRole, FieldsIsFamilyFor, FieldsFamily>>>> retType = new TypeReference<List<List<Tuple3<FieldsRole, FieldsIsFamilyFor, FieldsFamily>>>>() {};
        List<List<Tuple3<FieldsRole, FieldsIsFamilyFor, FieldsFamily>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_DeterminesFunctionOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsFormedOf</p>
     * <pre>
     * This relationship connects each feature to the atomic regulon to
     * which it belongs.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAtomicRegulon FieldsAtomicRegulon} (original type "fields_AtomicRegulon"), type {@link us.kbase.cdmientityapi.FieldsIsFormedOf FieldsIsFormedOf} (original type "fields_IsFormedOf"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAtomicRegulon, FieldsIsFormedOf, FieldsFeature>> getRelationshipIsFormedOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAtomicRegulon, FieldsIsFormedOf, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsAtomicRegulon, FieldsIsFormedOf, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsAtomicRegulon, FieldsIsFormedOf, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsFormedOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsFormedInto</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsFormedOf FieldsIsFormedOf} (original type "fields_IsFormedOf"), type {@link us.kbase.cdmientityapi.FieldsAtomicRegulon FieldsAtomicRegulon} (original type "fields_AtomicRegulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsFormedOf, FieldsAtomicRegulon>> getRelationshipIsFormedInto(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsFormedOf, FieldsAtomicRegulon>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsFormedOf, FieldsAtomicRegulon>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsFormedOf, FieldsAtomicRegulon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsFormedInto", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsFunctionalIn</p>
     * <pre>
     * This relationship connects a role with the features in which
     * it plays a functional part.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role"), type {@link us.kbase.cdmientityapi.FieldsIsFunctionalIn FieldsIsFunctionalIn} (original type "fields_IsFunctionalIn"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRole, FieldsIsFunctionalIn, FieldsFeature>> getRelationshipIsFunctionalIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRole, FieldsIsFunctionalIn, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsRole, FieldsIsFunctionalIn, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsRole, FieldsIsFunctionalIn, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsFunctionalIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasFunctional</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsFunctionalIn FieldsIsFunctionalIn} (original type "fields_IsFunctionalIn"), type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsFunctionalIn, FieldsRole>> getRelationshipHasFunctional(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsFunctionalIn, FieldsRole>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsFunctionalIn, FieldsRole>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsFunctionalIn, FieldsRole>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasFunctional", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsGroupFor</p>
     * <pre>
     * The recursive IsGroupFor relationship organizes
     * taxonomic groupings into a hierarchy based on the standard organism
     * taxonomy.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTaxonomicGrouping FieldsTaxonomicGrouping} (original type "fields_TaxonomicGrouping"), type {@link us.kbase.cdmientityapi.FieldsIsGroupFor FieldsIsGroupFor} (original type "fields_IsGroupFor"), type {@link us.kbase.cdmientityapi.FieldsTaxonomicGrouping FieldsTaxonomicGrouping} (original type "fields_TaxonomicGrouping")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTaxonomicGrouping, FieldsIsGroupFor, FieldsTaxonomicGrouping>> getRelationshipIsGroupFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsGroupFor, FieldsTaxonomicGrouping>>>> retType = new TypeReference<List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsGroupFor, FieldsTaxonomicGrouping>>>>() {};
        List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsGroupFor, FieldsTaxonomicGrouping>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsGroupFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsInGroup</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTaxonomicGrouping FieldsTaxonomicGrouping} (original type "fields_TaxonomicGrouping"), type {@link us.kbase.cdmientityapi.FieldsIsGroupFor FieldsIsGroupFor} (original type "fields_IsGroupFor"), type {@link us.kbase.cdmientityapi.FieldsTaxonomicGrouping FieldsTaxonomicGrouping} (original type "fields_TaxonomicGrouping")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTaxonomicGrouping, FieldsIsGroupFor, FieldsTaxonomicGrouping>> getRelationshipIsInGroup(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsGroupFor, FieldsTaxonomicGrouping>>>> retType = new TypeReference<List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsGroupFor, FieldsTaxonomicGrouping>>>>() {};
        List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsGroupFor, FieldsTaxonomicGrouping>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsInGroup", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsGroupingOf</p>
     * <pre>
     * The IsGroupingOf relationship describes which
     * associations are part of a particular association
     * dataset.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAssociationDataset FieldsAssociationDataset} (original type "fields_AssociationDataset"), type {@link us.kbase.cdmientityapi.FieldsIsGroupingOf FieldsIsGroupingOf} (original type "fields_IsGroupingOf"), type {@link us.kbase.cdmientityapi.FieldsAssociation FieldsAssociation} (original type "fields_Association")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAssociationDataset, FieldsIsGroupingOf, FieldsAssociation>> getRelationshipIsGroupingOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAssociationDataset, FieldsIsGroupingOf, FieldsAssociation>>>> retType = new TypeReference<List<List<Tuple3<FieldsAssociationDataset, FieldsIsGroupingOf, FieldsAssociation>>>>() {};
        List<List<Tuple3<FieldsAssociationDataset, FieldsIsGroupingOf, FieldsAssociation>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsGroupingOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_InAssociationDataset</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAssociation FieldsAssociation} (original type "fields_Association"), type {@link us.kbase.cdmientityapi.FieldsIsGroupingOf FieldsIsGroupingOf} (original type "fields_IsGroupingOf"), type {@link us.kbase.cdmientityapi.FieldsAssociationDataset FieldsAssociationDataset} (original type "fields_AssociationDataset")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAssociation, FieldsIsGroupingOf, FieldsAssociationDataset>> getRelationshipInAssociationDataset(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAssociation, FieldsIsGroupingOf, FieldsAssociationDataset>>>> retType = new TypeReference<List<List<Tuple3<FieldsAssociation, FieldsIsGroupingOf, FieldsAssociationDataset>>>>() {};
        List<List<Tuple3<FieldsAssociation, FieldsIsGroupingOf, FieldsAssociationDataset>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_InAssociationDataset", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsImplementedBy</p>
     * <pre>
     * This relationship connects a variant to the physical machines
     * that implement it in the genomes. A variant is implemented by many
     * machines, but a machine belongs to only one variant.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsVariant FieldsVariant} (original type "fields_Variant"), type {@link us.kbase.cdmientityapi.FieldsIsImplementedBy FieldsIsImplementedBy} (original type "fields_IsImplementedBy"), type {@link us.kbase.cdmientityapi.FieldsSSRow FieldsSSRow} (original type "fields_SSRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsVariant, FieldsIsImplementedBy, FieldsSSRow>> getRelationshipIsImplementedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsVariant, FieldsIsImplementedBy, FieldsSSRow>>>> retType = new TypeReference<List<List<Tuple3<FieldsVariant, FieldsIsImplementedBy, FieldsSSRow>>>>() {};
        List<List<Tuple3<FieldsVariant, FieldsIsImplementedBy, FieldsSSRow>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsImplementedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Implements</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSSRow FieldsSSRow} (original type "fields_SSRow"), type {@link us.kbase.cdmientityapi.FieldsIsImplementedBy FieldsIsImplementedBy} (original type "fields_IsImplementedBy"), type {@link us.kbase.cdmientityapi.FieldsVariant FieldsVariant} (original type "fields_Variant")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSSRow, FieldsIsImplementedBy, FieldsVariant>> getRelationshipImplements(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSSRow, FieldsIsImplementedBy, FieldsVariant>>>> retType = new TypeReference<List<List<Tuple3<FieldsSSRow, FieldsIsImplementedBy, FieldsVariant>>>>() {};
        List<List<Tuple3<FieldsSSRow, FieldsIsImplementedBy, FieldsVariant>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Implements", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsInOperon</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =item rank
     * The rank (order) of this feature in the operon.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsInOperon FieldsIsInOperon} (original type "fields_IsInOperon"), type {@link us.kbase.cdmientityapi.FieldsOperon FieldsOperon} (original type "fields_Operon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsInOperon, FieldsOperon>> getRelationshipIsInOperon(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsInOperon, FieldsOperon>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsInOperon, FieldsOperon>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsInOperon, FieldsOperon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsInOperon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_OperonContains</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsOperon FieldsOperon} (original type "fields_Operon"), type {@link us.kbase.cdmientityapi.FieldsIsInOperon FieldsIsInOperon} (original type "fields_IsInOperon"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsOperon, FieldsIsInOperon, FieldsFeature>> getRelationshipOperonContains(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsOperon, FieldsIsInOperon, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsOperon, FieldsIsInOperon, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsOperon, FieldsIsInOperon, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_OperonContains", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsInPair</p>
     * <pre>
     * A pairing contains exactly two protein sequences. A protein
     * sequence can belong to multiple pairings. When going from a protein
     * sequence to its pairings, they are presented in alphabetical order
     * by sequence key.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsInPair FieldsIsInPair} (original type "fields_IsInPair"), type {@link us.kbase.cdmientityapi.FieldsPairing FieldsPairing} (original type "fields_Pairing")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsInPair, FieldsPairing>> getRelationshipIsInPair(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsInPair, FieldsPairing>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsInPair, FieldsPairing>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsInPair, FieldsPairing>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsInPair", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsPairOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPairing FieldsPairing} (original type "fields_Pairing"), type {@link us.kbase.cdmientityapi.FieldsIsInPair FieldsIsInPair} (original type "fields_IsInPair"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPairing, FieldsIsInPair, FieldsFeature>> getRelationshipIsPairOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPairing, FieldsIsInPair, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsPairing, FieldsIsInPair, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsPairing, FieldsIsInPair, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsPairOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsInstantiatedBy</p>
     * <pre>
     * This relationship connects a subcellular location to the instances
     * of that location that occur in models.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsLocation FieldsLocation} (original type "fields_Location"), type {@link us.kbase.cdmientityapi.FieldsIsInstantiatedBy FieldsIsInstantiatedBy} (original type "fields_IsInstantiatedBy"), type {@link us.kbase.cdmientityapi.FieldsLocationInstance FieldsLocationInstance} (original type "fields_LocationInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsLocation, FieldsIsInstantiatedBy, FieldsLocationInstance>> getRelationshipIsInstantiatedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsLocation, FieldsIsInstantiatedBy, FieldsLocationInstance>>>> retType = new TypeReference<List<List<Tuple3<FieldsLocation, FieldsIsInstantiatedBy, FieldsLocationInstance>>>>() {};
        List<List<Tuple3<FieldsLocation, FieldsIsInstantiatedBy, FieldsLocationInstance>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsInstantiatedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsInstanceOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsLocationInstance FieldsLocationInstance} (original type "fields_LocationInstance"), type {@link us.kbase.cdmientityapi.FieldsIsInstantiatedBy FieldsIsInstantiatedBy} (original type "fields_IsInstantiatedBy"), type {@link us.kbase.cdmientityapi.FieldsLocation FieldsLocation} (original type "fields_Location")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsLocationInstance, FieldsIsInstantiatedBy, FieldsLocation>> getRelationshipIsInstanceOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsLocationInstance, FieldsIsInstantiatedBy, FieldsLocation>>>> retType = new TypeReference<List<List<Tuple3<FieldsLocationInstance, FieldsIsInstantiatedBy, FieldsLocation>>>>() {};
        List<List<Tuple3<FieldsLocationInstance, FieldsIsInstantiatedBy, FieldsLocation>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsInstanceOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsLocatedIn</p>
     * <pre>
     * A feature is a set of DNA sequence fragments, the location of
     * which are specified by the fields of this relationship. Most features
     * are a single contiguous fragment, so they are located in only one
     * DNA sequence; however, for search optimization reasons, fragments
     * have a maximum length, so even a single contiguous feature may
     * participate in this relationship multiple times. Thus, it is better
     * to use the CDMI API methods to get feature positions and sequences
     * as those methods rejoin the fragements for contiguous features. A few
     * features belong to multiple DNA sequences. In that case, however, all
     * the DNA sequences belong to the same genome. A DNA sequence itself
     * will frequently have thousands of features connected to it.
     * It has the following fields:
     * =over 4
     * =item ordinal
     * Sequence number of this segment, starting from 1 and proceeding sequentially forward from there.
     * =item begin
     * Index (1-based) of the first residue in the contig that belongs to the segment.
     * =item len
     * Length of this segment.
     * =item dir
     * Direction (strand) of the segment: "+" if it is forward and "-" if it is backward.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsLocatedIn FieldsIsLocatedIn} (original type "fields_IsLocatedIn"), type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsLocatedIn, FieldsContig>> getRelationshipIsLocatedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsLocatedIn, FieldsContig>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsLocatedIn, FieldsContig>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsLocatedIn, FieldsContig>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsLocatedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsLocusFor</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig"), type {@link us.kbase.cdmientityapi.FieldsIsLocatedIn FieldsIsLocatedIn} (original type "fields_IsLocatedIn"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsContig, FieldsIsLocatedIn, FieldsFeature>> getRelationshipIsLocusFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsContig, FieldsIsLocatedIn, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsContig, FieldsIsLocatedIn, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsContig, FieldsIsLocatedIn, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsLocusFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsMeasurementMethodOf</p>
     * <pre>
     * The IsMeasurementMethodOf relationship describes which protocol
     * was used to take a measurement.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProtocol FieldsProtocol} (original type "fields_Protocol"), type {@link us.kbase.cdmientityapi.FieldsIsMeasurementMethodOf FieldsIsMeasurementMethodOf} (original type "fields_IsMeasurementMethodOf"), type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProtocol, FieldsIsMeasurementMethodOf, FieldsMeasurement>> getRelationshipIsMeasurementMethodOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProtocol, FieldsIsMeasurementMethodOf, FieldsMeasurement>>>> retType = new TypeReference<List<List<Tuple3<FieldsProtocol, FieldsIsMeasurementMethodOf, FieldsMeasurement>>>>() {};
        List<List<Tuple3<FieldsProtocol, FieldsIsMeasurementMethodOf, FieldsMeasurement>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsMeasurementMethodOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_WasMeasuredBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement"), type {@link us.kbase.cdmientityapi.FieldsIsMeasurementMethodOf FieldsIsMeasurementMethodOf} (original type "fields_IsMeasurementMethodOf"), type {@link us.kbase.cdmientityapi.FieldsProtocol FieldsProtocol} (original type "fields_Protocol")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsMeasurement, FieldsIsMeasurementMethodOf, FieldsProtocol>> getRelationshipWasMeasuredBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsIsMeasurementMethodOf, FieldsProtocol>>>> retType = new TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsIsMeasurementMethodOf, FieldsProtocol>>>>() {};
        List<List<Tuple3<FieldsMeasurement, FieldsIsMeasurementMethodOf, FieldsProtocol>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_WasMeasuredBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsModeledBy</p>
     * <pre>
     * A genome can be modeled by many different models, but a model belongs
     * to only one genome.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsIsModeledBy FieldsIsModeledBy} (original type "fields_IsModeledBy"), type {@link us.kbase.cdmientityapi.FieldsModel FieldsModel} (original type "fields_Model")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsIsModeledBy, FieldsModel>> getRelationshipIsModeledBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsModeledBy, FieldsModel>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsModeledBy, FieldsModel>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsIsModeledBy, FieldsModel>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsModeledBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Models</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsModel FieldsModel} (original type "fields_Model"), type {@link us.kbase.cdmientityapi.FieldsIsModeledBy FieldsIsModeledBy} (original type "fields_IsModeledBy"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsModel, FieldsIsModeledBy, FieldsGenome>> getRelationshipModels(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsModel, FieldsIsModeledBy, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsModel, FieldsIsModeledBy, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsModel, FieldsIsModeledBy, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Models", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsModifiedToBuildAlignment</p>
     * <pre>
     * Relates an alignment to other alignments built from it.
     * It has the following fields:
     * =over 4
     * =item modification_type
     * description of how the alignment was modified
     * =item modification_value
     * description of any parameters used to derive the modification
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment"), type {@link us.kbase.cdmientityapi.FieldsIsModifiedToBuildAlignment FieldsIsModifiedToBuildAlignment} (original type "fields_IsModifiedToBuildAlignment"), type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignment, FieldsIsModifiedToBuildAlignment, FieldsAlignment>> getRelationshipIsModifiedToBuildAlignment(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignment, FieldsIsModifiedToBuildAlignment, FieldsAlignment>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignment, FieldsIsModifiedToBuildAlignment, FieldsAlignment>>>>() {};
        List<List<Tuple3<FieldsAlignment, FieldsIsModifiedToBuildAlignment, FieldsAlignment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsModifiedToBuildAlignment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsModificationOfAlignment</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment"), type {@link us.kbase.cdmientityapi.FieldsIsModifiedToBuildAlignment FieldsIsModifiedToBuildAlignment} (original type "fields_IsModifiedToBuildAlignment"), type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignment, FieldsIsModifiedToBuildAlignment, FieldsAlignment>> getRelationshipIsModificationOfAlignment(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignment, FieldsIsModifiedToBuildAlignment, FieldsAlignment>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignment, FieldsIsModifiedToBuildAlignment, FieldsAlignment>>>>() {};
        List<List<Tuple3<FieldsAlignment, FieldsIsModifiedToBuildAlignment, FieldsAlignment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsModificationOfAlignment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsModifiedToBuildTree</p>
     * <pre>
     * Relates a tree to other trees built from it.
     * It has the following fields:
     * =over 4
     * =item modification_type
     * description of how the tree was modified (rerooted, annotated, etc.)
     * =item modification_value
     * description of any parameters used to derive the modification
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree"), type {@link us.kbase.cdmientityapi.FieldsIsModifiedToBuildTree FieldsIsModifiedToBuildTree} (original type "fields_IsModifiedToBuildTree"), type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTree, FieldsIsModifiedToBuildTree, FieldsTree>> getRelationshipIsModifiedToBuildTree(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTree, FieldsIsModifiedToBuildTree, FieldsTree>>>> retType = new TypeReference<List<List<Tuple3<FieldsTree, FieldsIsModifiedToBuildTree, FieldsTree>>>>() {};
        List<List<Tuple3<FieldsTree, FieldsIsModifiedToBuildTree, FieldsTree>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsModifiedToBuildTree", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsModificationOfTree</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree"), type {@link us.kbase.cdmientityapi.FieldsIsModifiedToBuildTree FieldsIsModifiedToBuildTree} (original type "fields_IsModifiedToBuildTree"), type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTree, FieldsIsModifiedToBuildTree, FieldsTree>> getRelationshipIsModificationOfTree(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTree, FieldsIsModifiedToBuildTree, FieldsTree>>>> retType = new TypeReference<List<List<Tuple3<FieldsTree, FieldsIsModifiedToBuildTree, FieldsTree>>>>() {};
        List<List<Tuple3<FieldsTree, FieldsIsModifiedToBuildTree, FieldsTree>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsModificationOfTree", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsOwnerOf</p>
     * <pre>
     * This relationship connects a genome to the features it
     * contains. Though technically redundant (the information is
     * available from the feature's contigs), it simplifies the
     * extremely common process of finding all features for a
     * genome.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsIsOwnerOf FieldsIsOwnerOf} (original type "fields_IsOwnerOf"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsIsOwnerOf, FieldsFeature>> getRelationshipIsOwnerOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsOwnerOf, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsOwnerOf, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsIsOwnerOf, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsOwnerOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsOwnedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsOwnerOf FieldsIsOwnerOf} (original type "fields_IsOwnerOf"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsOwnerOf, FieldsGenome>> getRelationshipIsOwnedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsOwnerOf, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsOwnerOf, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsOwnerOf, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsOwnedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsParticipatingAt</p>
     * <pre>
     * This relationship connects a localized compound to the
     * location in which it occurs during one or more reactions.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsLocation FieldsLocation} (original type "fields_Location"), type {@link us.kbase.cdmientityapi.FieldsIsParticipatingAt FieldsIsParticipatingAt} (original type "fields_IsParticipatingAt"), type {@link us.kbase.cdmientityapi.FieldsLocalizedCompound FieldsLocalizedCompound} (original type "fields_LocalizedCompound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsLocation, FieldsIsParticipatingAt, FieldsLocalizedCompound>> getRelationshipIsParticipatingAt(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsLocation, FieldsIsParticipatingAt, FieldsLocalizedCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsLocation, FieldsIsParticipatingAt, FieldsLocalizedCompound>>>>() {};
        List<List<Tuple3<FieldsLocation, FieldsIsParticipatingAt, FieldsLocalizedCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsParticipatingAt", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ParticipatesAt</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsLocalizedCompound FieldsLocalizedCompound} (original type "fields_LocalizedCompound"), type {@link us.kbase.cdmientityapi.FieldsIsParticipatingAt FieldsIsParticipatingAt} (original type "fields_IsParticipatingAt"), type {@link us.kbase.cdmientityapi.FieldsLocation FieldsLocation} (original type "fields_Location")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsLocalizedCompound, FieldsIsParticipatingAt, FieldsLocation>> getRelationshipParticipatesAt(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsLocalizedCompound, FieldsIsParticipatingAt, FieldsLocation>>>> retType = new TypeReference<List<List<Tuple3<FieldsLocalizedCompound, FieldsIsParticipatingAt, FieldsLocation>>>>() {};
        List<List<Tuple3<FieldsLocalizedCompound, FieldsIsParticipatingAt, FieldsLocation>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ParticipatesAt", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsProteinFor</p>
     * <pre>
     * This relationship connects a peg feature to the protein
     * sequence it produces (if any). Only peg features participate in this
     * relationship. A single protein sequence will frequently be produced
     * by many features.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence"), type {@link us.kbase.cdmientityapi.FieldsIsProteinFor FieldsIsProteinFor} (original type "fields_IsProteinFor"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProteinSequence, FieldsIsProteinFor, FieldsFeature>> getRelationshipIsProteinFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsIsProteinFor, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsProteinSequence, FieldsIsProteinFor, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsProteinSequence, FieldsIsProteinFor, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsProteinFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Produces</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsProteinFor FieldsIsProteinFor} (original type "fields_IsProteinFor"), type {@link us.kbase.cdmientityapi.FieldsProteinSequence FieldsProteinSequence} (original type "fields_ProteinSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsProteinFor, FieldsProteinSequence>> getRelationshipProduces(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsProteinFor, FieldsProteinSequence>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsProteinFor, FieldsProteinSequence>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsProteinFor, FieldsProteinSequence>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Produces", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsReagentIn</p>
     * <pre>
     * This relationship connects a compound instance to the reaction instance
     * in which it is transformed.
     * It has the following fields:
     * =over 4
     * =item coefficient
     * Number of molecules of the compound that participate in a single instance of the reaction. For example, if a reaction produces two water molecules, the stoichiometry of water for the reaction would be two. When a reaction is written on paper in chemical notation, the stoichiometry is the number next to the chemical formula of the compound. The value is negative for substrates and positive for products.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompoundInstance FieldsCompoundInstance} (original type "fields_CompoundInstance"), type {@link us.kbase.cdmientityapi.FieldsIsReagentIn FieldsIsReagentIn} (original type "fields_IsReagentIn"), type {@link us.kbase.cdmientityapi.FieldsReactionInstance FieldsReactionInstance} (original type "fields_ReactionInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompoundInstance, FieldsIsReagentIn, FieldsReactionInstance>> getRelationshipIsReagentIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompoundInstance, FieldsIsReagentIn, FieldsReactionInstance>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompoundInstance, FieldsIsReagentIn, FieldsReactionInstance>>>>() {};
        List<List<Tuple3<FieldsCompoundInstance, FieldsIsReagentIn, FieldsReactionInstance>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsReagentIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Targets</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsReactionInstance FieldsReactionInstance} (original type "fields_ReactionInstance"), type {@link us.kbase.cdmientityapi.FieldsIsReagentIn FieldsIsReagentIn} (original type "fields_IsReagentIn"), type {@link us.kbase.cdmientityapi.FieldsCompoundInstance FieldsCompoundInstance} (original type "fields_CompoundInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsReactionInstance, FieldsIsReagentIn, FieldsCompoundInstance>> getRelationshipTargets(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsReactionInstance, FieldsIsReagentIn, FieldsCompoundInstance>>>> retType = new TypeReference<List<List<Tuple3<FieldsReactionInstance, FieldsIsReagentIn, FieldsCompoundInstance>>>>() {};
        List<List<Tuple3<FieldsReactionInstance, FieldsIsReagentIn, FieldsCompoundInstance>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Targets", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRealLocationOf</p>
     * <pre>
     * This relationship connects a specific instance of a compound in a model
     * to the specific instance of the model subcellular location where the compound exists.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsLocationInstance FieldsLocationInstance} (original type "fields_LocationInstance"), type {@link us.kbase.cdmientityapi.FieldsIsRealLocationOf FieldsIsRealLocationOf} (original type "fields_IsRealLocationOf"), type {@link us.kbase.cdmientityapi.FieldsCompoundInstance FieldsCompoundInstance} (original type "fields_CompoundInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsLocationInstance, FieldsIsRealLocationOf, FieldsCompoundInstance>> getRelationshipIsRealLocationOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsLocationInstance, FieldsIsRealLocationOf, FieldsCompoundInstance>>>> retType = new TypeReference<List<List<Tuple3<FieldsLocationInstance, FieldsIsRealLocationOf, FieldsCompoundInstance>>>>() {};
        List<List<Tuple3<FieldsLocationInstance, FieldsIsRealLocationOf, FieldsCompoundInstance>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRealLocationOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasRealLocationIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompoundInstance FieldsCompoundInstance} (original type "fields_CompoundInstance"), type {@link us.kbase.cdmientityapi.FieldsIsRealLocationOf FieldsIsRealLocationOf} (original type "fields_IsRealLocationOf"), type {@link us.kbase.cdmientityapi.FieldsLocationInstance FieldsLocationInstance} (original type "fields_LocationInstance")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompoundInstance, FieldsIsRealLocationOf, FieldsLocationInstance>> getRelationshipHasRealLocationIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompoundInstance, FieldsIsRealLocationOf, FieldsLocationInstance>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompoundInstance, FieldsIsRealLocationOf, FieldsLocationInstance>>>>() {};
        List<List<Tuple3<FieldsCompoundInstance, FieldsIsRealLocationOf, FieldsLocationInstance>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasRealLocationIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsReferencedBy</p>
     * <pre>
     * This relationship associates each observational unit with the reference
     * genome that it will be compared to.  All variations will be differences
     * between the observational unit and the reference.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsIsReferencedBy FieldsIsReferencedBy} (original type "fields_IsReferencedBy"), type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsIsReferencedBy, FieldsObservationalUnit>> getRelationshipIsReferencedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsReferencedBy, FieldsObservationalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsReferencedBy, FieldsObservationalUnit>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsIsReferencedBy, FieldsObservationalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsReferencedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_UsesReference</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit"), type {@link us.kbase.cdmientityapi.FieldsIsReferencedBy FieldsIsReferencedBy} (original type "fields_IsReferencedBy"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsObservationalUnit, FieldsIsReferencedBy, FieldsGenome>> getRelationshipUsesReference(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsIsReferencedBy, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsIsReferencedBy, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsObservationalUnit, FieldsIsReferencedBy, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_UsesReference", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRegulatedIn</p>
     * <pre>
     * This relationship connects a feature to the set of coregulated features.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsRegulatedIn FieldsIsRegulatedIn} (original type "fields_IsRegulatedIn"), type {@link us.kbase.cdmientityapi.FieldsCoregulatedSet FieldsCoregulatedSet} (original type "fields_CoregulatedSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsRegulatedIn, FieldsCoregulatedSet>> getRelationshipIsRegulatedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsRegulatedIn, FieldsCoregulatedSet>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsRegulatedIn, FieldsCoregulatedSet>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsRegulatedIn, FieldsCoregulatedSet>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRegulatedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRegulatedSetOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCoregulatedSet FieldsCoregulatedSet} (original type "fields_CoregulatedSet"), type {@link us.kbase.cdmientityapi.FieldsIsRegulatedIn FieldsIsRegulatedIn} (original type "fields_IsRegulatedIn"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCoregulatedSet, FieldsIsRegulatedIn, FieldsFeature>> getRelationshipIsRegulatedSetOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCoregulatedSet, FieldsIsRegulatedIn, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsCoregulatedSet, FieldsIsRegulatedIn, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsCoregulatedSet, FieldsIsRegulatedIn, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRegulatedSetOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRegulatorFor</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulator FieldsRegulator} (original type "fields_Regulator"), type {@link us.kbase.cdmientityapi.FieldsIsRegulatorFor FieldsIsRegulatorFor} (original type "fields_IsRegulatorFor"), type {@link us.kbase.cdmientityapi.FieldsRegulog FieldsRegulog} (original type "fields_Regulog")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulator, FieldsIsRegulatorFor, FieldsRegulog>> getRelationshipIsRegulatorFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulator, FieldsIsRegulatorFor, FieldsRegulog>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulator, FieldsIsRegulatorFor, FieldsRegulog>>>>() {};
        List<List<Tuple3<FieldsRegulator, FieldsIsRegulatorFor, FieldsRegulog>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRegulatorFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasRegulator</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulog FieldsRegulog} (original type "fields_Regulog"), type {@link us.kbase.cdmientityapi.FieldsIsRegulatorFor FieldsIsRegulatorFor} (original type "fields_IsRegulatorFor"), type {@link us.kbase.cdmientityapi.FieldsRegulator FieldsRegulator} (original type "fields_Regulator")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulog, FieldsIsRegulatorFor, FieldsRegulator>> getRelationshipHasRegulator(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulog, FieldsIsRegulatorFor, FieldsRegulator>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulog, FieldsIsRegulatorFor, FieldsRegulator>>>>() {};
        List<List<Tuple3<FieldsRegulog, FieldsIsRegulatorFor, FieldsRegulator>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasRegulator", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRegulatorForRegulon</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulator FieldsRegulator} (original type "fields_Regulator"), type {@link us.kbase.cdmientityapi.FieldsIsRegulatorForRegulon FieldsIsRegulatorForRegulon} (original type "fields_IsRegulatorForRegulon"), type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulator, FieldsIsRegulatorForRegulon, FieldsRegulon>> getRelationshipIsRegulatorForRegulon(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulator, FieldsIsRegulatorForRegulon, FieldsRegulon>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulator, FieldsIsRegulatorForRegulon, FieldsRegulon>>>>() {};
        List<List<Tuple3<FieldsRegulator, FieldsIsRegulatorForRegulon, FieldsRegulon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRegulatorForRegulon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ReglonHasRegulator</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon"), type {@link us.kbase.cdmientityapi.FieldsIsRegulatorForRegulon FieldsIsRegulatorForRegulon} (original type "fields_IsRegulatorForRegulon"), type {@link us.kbase.cdmientityapi.FieldsRegulator FieldsRegulator} (original type "fields_Regulator")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulon, FieldsIsRegulatorForRegulon, FieldsRegulator>> getRelationshipReglonHasRegulator(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulon, FieldsIsRegulatorForRegulon, FieldsRegulator>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulon, FieldsIsRegulatorForRegulon, FieldsRegulator>>>>() {};
        List<List<Tuple3<FieldsRegulon, FieldsIsRegulatorForRegulon, FieldsRegulator>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ReglonHasRegulator", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRegulatorySiteFor</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature"), type {@link us.kbase.cdmientityapi.FieldsIsRegulatorySiteFor FieldsIsRegulatorySiteFor} (original type "fields_IsRegulatorySiteFor"), type {@link us.kbase.cdmientityapi.FieldsOperon FieldsOperon} (original type "fields_Operon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsFeature, FieldsIsRegulatorySiteFor, FieldsOperon>> getRelationshipIsRegulatorySiteFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsRegulatorySiteFor, FieldsOperon>>>> retType = new TypeReference<List<List<Tuple3<FieldsFeature, FieldsIsRegulatorySiteFor, FieldsOperon>>>>() {};
        List<List<Tuple3<FieldsFeature, FieldsIsRegulatorySiteFor, FieldsOperon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRegulatorySiteFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasRegulatorySite</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsOperon FieldsOperon} (original type "fields_Operon"), type {@link us.kbase.cdmientityapi.FieldsIsRegulatorySiteFor FieldsIsRegulatorySiteFor} (original type "fields_IsRegulatorySiteFor"), type {@link us.kbase.cdmientityapi.FieldsFeature FieldsFeature} (original type "fields_Feature")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsOperon, FieldsIsRegulatorySiteFor, FieldsFeature>> getRelationshipHasRegulatorySite(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsOperon, FieldsIsRegulatorySiteFor, FieldsFeature>>>> retType = new TypeReference<List<List<Tuple3<FieldsOperon, FieldsIsRegulatorySiteFor, FieldsFeature>>>>() {};
        List<List<Tuple3<FieldsOperon, FieldsIsRegulatorySiteFor, FieldsFeature>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasRegulatorySite", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRelevantFor</p>
     * <pre>
     * This relationship connects a diagram to the subsystems that are depicted on
     * it. Only diagrams which are useful in curating or annotation the subsystem are
     * specified in this relationship.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsDiagram FieldsDiagram} (original type "fields_Diagram"), type {@link us.kbase.cdmientityapi.FieldsIsRelevantFor FieldsIsRelevantFor} (original type "fields_IsRelevantFor"), type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsDiagram, FieldsIsRelevantFor, FieldsSubsystem>> getRelationshipIsRelevantFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsDiagram, FieldsIsRelevantFor, FieldsSubsystem>>>> retType = new TypeReference<List<List<Tuple3<FieldsDiagram, FieldsIsRelevantFor, FieldsSubsystem>>>>() {};
        List<List<Tuple3<FieldsDiagram, FieldsIsRelevantFor, FieldsSubsystem>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRelevantFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRelevantTo</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem"), type {@link us.kbase.cdmientityapi.FieldsIsRelevantFor FieldsIsRelevantFor} (original type "fields_IsRelevantFor"), type {@link us.kbase.cdmientityapi.FieldsDiagram FieldsDiagram} (original type "fields_Diagram")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSubsystem, FieldsIsRelevantFor, FieldsDiagram>> getRelationshipIsRelevantTo(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsIsRelevantFor, FieldsDiagram>>>> retType = new TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsIsRelevantFor, FieldsDiagram>>>>() {};
        List<List<Tuple3<FieldsSubsystem, FieldsIsRelevantFor, FieldsDiagram>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRelevantTo", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRepresentedBy</p>
     * <pre>
     * This relationship associates observational units with a genus,
     * species, strain, and/or variety that was the source material.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTaxonomicGrouping FieldsTaxonomicGrouping} (original type "fields_TaxonomicGrouping"), type {@link us.kbase.cdmientityapi.FieldsIsRepresentedBy FieldsIsRepresentedBy} (original type "fields_IsRepresentedBy"), type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTaxonomicGrouping, FieldsIsRepresentedBy, FieldsObservationalUnit>> getRelationshipIsRepresentedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsRepresentedBy, FieldsObservationalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsRepresentedBy, FieldsObservationalUnit>>>>() {};
        List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsRepresentedBy, FieldsObservationalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRepresentedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_DefinedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsObservationalUnit FieldsObservationalUnit} (original type "fields_ObservationalUnit"), type {@link us.kbase.cdmientityapi.FieldsIsRepresentedBy FieldsIsRepresentedBy} (original type "fields_IsRepresentedBy"), type {@link us.kbase.cdmientityapi.FieldsTaxonomicGrouping FieldsTaxonomicGrouping} (original type "fields_TaxonomicGrouping")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsObservationalUnit, FieldsIsRepresentedBy, FieldsTaxonomicGrouping>> getRelationshipDefinedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsIsRepresentedBy, FieldsTaxonomicGrouping>>>> retType = new TypeReference<List<List<Tuple3<FieldsObservationalUnit, FieldsIsRepresentedBy, FieldsTaxonomicGrouping>>>>() {};
        List<List<Tuple3<FieldsObservationalUnit, FieldsIsRepresentedBy, FieldsTaxonomicGrouping>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_DefinedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRoleOf</p>
     * <pre>
     * This relationship connects a role to the machine roles that
     * represent its appearance in a molecular machine. A machine role has
     * exactly one associated role, but a role may be represented by many
     * machine roles.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role"), type {@link us.kbase.cdmientityapi.FieldsIsRoleOf FieldsIsRoleOf} (original type "fields_IsRoleOf"), type {@link us.kbase.cdmientityapi.FieldsSSCell FieldsSSCell} (original type "fields_SSCell")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRole, FieldsIsRoleOf, FieldsSSCell>> getRelationshipIsRoleOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRole, FieldsIsRoleOf, FieldsSSCell>>>> retType = new TypeReference<List<List<Tuple3<FieldsRole, FieldsIsRoleOf, FieldsSSCell>>>>() {};
        List<List<Tuple3<FieldsRole, FieldsIsRoleOf, FieldsSSCell>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRoleOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasRole</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSSCell FieldsSSCell} (original type "fields_SSCell"), type {@link us.kbase.cdmientityapi.FieldsIsRoleOf FieldsIsRoleOf} (original type "fields_IsRoleOf"), type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSSCell, FieldsIsRoleOf, FieldsRole>> getRelationshipHasRole(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSSCell, FieldsIsRoleOf, FieldsRole>>>> retType = new TypeReference<List<List<Tuple3<FieldsSSCell, FieldsIsRoleOf, FieldsRole>>>>() {};
        List<List<Tuple3<FieldsSSCell, FieldsIsRoleOf, FieldsRole>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasRole", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRowOf</p>
     * <pre>
     * This relationship connects a subsystem spreadsheet row to its
     * constituent spreadsheet cells.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSSRow FieldsSSRow} (original type "fields_SSRow"), type {@link us.kbase.cdmientityapi.FieldsIsRowOf FieldsIsRowOf} (original type "fields_IsRowOf"), type {@link us.kbase.cdmientityapi.FieldsSSCell FieldsSSCell} (original type "fields_SSCell")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSSRow, FieldsIsRowOf, FieldsSSCell>> getRelationshipIsRowOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSSRow, FieldsIsRowOf, FieldsSSCell>>>> retType = new TypeReference<List<List<Tuple3<FieldsSSRow, FieldsIsRowOf, FieldsSSCell>>>>() {};
        List<List<Tuple3<FieldsSSRow, FieldsIsRowOf, FieldsSSCell>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRowOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsRoleFor</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSSCell FieldsSSCell} (original type "fields_SSCell"), type {@link us.kbase.cdmientityapi.FieldsIsRowOf FieldsIsRowOf} (original type "fields_IsRowOf"), type {@link us.kbase.cdmientityapi.FieldsSSRow FieldsSSRow} (original type "fields_SSRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSSCell, FieldsIsRowOf, FieldsSSRow>> getRelationshipIsRoleFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSSCell, FieldsIsRowOf, FieldsSSRow>>>> retType = new TypeReference<List<List<Tuple3<FieldsSSCell, FieldsIsRowOf, FieldsSSRow>>>>() {};
        List<List<Tuple3<FieldsSSCell, FieldsIsRowOf, FieldsSSRow>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsRoleFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsSequenceOf</p>
     * <pre>
     * This relationship connects a Contig as it occurs in a
     * genome to the Contig Sequence that represents the physical
     * DNA base pairs. A contig sequence may represent many contigs,
     * but each contig has only one sequence.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsContigSequence FieldsContigSequence} (original type "fields_ContigSequence"), type {@link us.kbase.cdmientityapi.FieldsIsSequenceOf FieldsIsSequenceOf} (original type "fields_IsSequenceOf"), type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsContigSequence, FieldsIsSequenceOf, FieldsContig>> getRelationshipIsSequenceOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsContigSequence, FieldsIsSequenceOf, FieldsContig>>>> retType = new TypeReference<List<List<Tuple3<FieldsContigSequence, FieldsIsSequenceOf, FieldsContig>>>>() {};
        List<List<Tuple3<FieldsContigSequence, FieldsIsSequenceOf, FieldsContig>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsSequenceOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasAsSequence</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig"), type {@link us.kbase.cdmientityapi.FieldsIsSequenceOf FieldsIsSequenceOf} (original type "fields_IsSequenceOf"), type {@link us.kbase.cdmientityapi.FieldsContigSequence FieldsContigSequence} (original type "fields_ContigSequence")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsContig, FieldsIsSequenceOf, FieldsContigSequence>> getRelationshipHasAsSequence(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsContig, FieldsIsSequenceOf, FieldsContigSequence>>>> retType = new TypeReference<List<List<Tuple3<FieldsContig, FieldsIsSequenceOf, FieldsContigSequence>>>>() {};
        List<List<Tuple3<FieldsContig, FieldsIsSequenceOf, FieldsContigSequence>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasAsSequence", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsSourceForAssociationDataset</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source"), type {@link us.kbase.cdmientityapi.FieldsIsSourceForAssociationDataset FieldsIsSourceForAssociationDataset} (original type "fields_IsSourceForAssociationDataset"), type {@link us.kbase.cdmientityapi.FieldsAssociationDataset FieldsAssociationDataset} (original type "fields_AssociationDataset")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSource, FieldsIsSourceForAssociationDataset, FieldsAssociationDataset>> getRelationshipIsSourceForAssociationDataset(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSource, FieldsIsSourceForAssociationDataset, FieldsAssociationDataset>>>> retType = new TypeReference<List<List<Tuple3<FieldsSource, FieldsIsSourceForAssociationDataset, FieldsAssociationDataset>>>>() {};
        List<List<Tuple3<FieldsSource, FieldsIsSourceForAssociationDataset, FieldsAssociationDataset>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsSourceForAssociationDataset", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_AssociationDatasetSourcedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAssociationDataset FieldsAssociationDataset} (original type "fields_AssociationDataset"), type {@link us.kbase.cdmientityapi.FieldsIsSourceForAssociationDataset FieldsIsSourceForAssociationDataset} (original type "fields_IsSourceForAssociationDataset"), type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAssociationDataset, FieldsIsSourceForAssociationDataset, FieldsSource>> getRelationshipAssociationDatasetSourcedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAssociationDataset, FieldsIsSourceForAssociationDataset, FieldsSource>>>> retType = new TypeReference<List<List<Tuple3<FieldsAssociationDataset, FieldsIsSourceForAssociationDataset, FieldsSource>>>>() {};
        List<List<Tuple3<FieldsAssociationDataset, FieldsIsSourceForAssociationDataset, FieldsSource>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_AssociationDatasetSourcedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsSubInstanceOf</p>
     * <pre>
     * This relationship connects a scenario to its subsystem it
     * validates. A scenario belongs to exactly one subsystem, but a
     * subsystem may have multiple scenarios.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem"), type {@link us.kbase.cdmientityapi.FieldsIsSubInstanceOf FieldsIsSubInstanceOf} (original type "fields_IsSubInstanceOf"), type {@link us.kbase.cdmientityapi.FieldsScenario FieldsScenario} (original type "fields_Scenario")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSubsystem, FieldsIsSubInstanceOf, FieldsScenario>> getRelationshipIsSubInstanceOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsIsSubInstanceOf, FieldsScenario>>>> retType = new TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsIsSubInstanceOf, FieldsScenario>>>>() {};
        List<List<Tuple3<FieldsSubsystem, FieldsIsSubInstanceOf, FieldsScenario>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsSubInstanceOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Validates</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsScenario FieldsScenario} (original type "fields_Scenario"), type {@link us.kbase.cdmientityapi.FieldsIsSubInstanceOf FieldsIsSubInstanceOf} (original type "fields_IsSubInstanceOf"), type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsScenario, FieldsIsSubInstanceOf, FieldsSubsystem>> getRelationshipValidates(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsScenario, FieldsIsSubInstanceOf, FieldsSubsystem>>>> retType = new TypeReference<List<List<Tuple3<FieldsScenario, FieldsIsSubInstanceOf, FieldsSubsystem>>>>() {};
        List<List<Tuple3<FieldsScenario, FieldsIsSubInstanceOf, FieldsSubsystem>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Validates", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsSummarizedBy</p>
     * <pre>
     * This relationship describes the statistical frequencies of the
     * most common alleles in various positions on the reference contig.
     * It has the following fields:
     * =over 4
     * =item position
     * Position in the reference contig where the trait has an impact.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig"), type {@link us.kbase.cdmientityapi.FieldsIsSummarizedBy FieldsIsSummarizedBy} (original type "fields_IsSummarizedBy"), type {@link us.kbase.cdmientityapi.FieldsAlleleFrequency FieldsAlleleFrequency} (original type "fields_AlleleFrequency")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsContig, FieldsIsSummarizedBy, FieldsAlleleFrequency>> getRelationshipIsSummarizedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsContig, FieldsIsSummarizedBy, FieldsAlleleFrequency>>>> retType = new TypeReference<List<List<Tuple3<FieldsContig, FieldsIsSummarizedBy, FieldsAlleleFrequency>>>>() {};
        List<List<Tuple3<FieldsContig, FieldsIsSummarizedBy, FieldsAlleleFrequency>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsSummarizedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Summarizes</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlleleFrequency FieldsAlleleFrequency} (original type "fields_AlleleFrequency"), type {@link us.kbase.cdmientityapi.FieldsIsSummarizedBy FieldsIsSummarizedBy} (original type "fields_IsSummarizedBy"), type {@link us.kbase.cdmientityapi.FieldsContig FieldsContig} (original type "fields_Contig")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlleleFrequency, FieldsIsSummarizedBy, FieldsContig>> getRelationshipSummarizes(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlleleFrequency, FieldsIsSummarizedBy, FieldsContig>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlleleFrequency, FieldsIsSummarizedBy, FieldsContig>>>>() {};
        List<List<Tuple3<FieldsAlleleFrequency, FieldsIsSummarizedBy, FieldsContig>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Summarizes", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsSuperclassOf</p>
     * <pre>
     * This is a recursive relationship that imposes a hierarchy on
     * the subsystem classes.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSubsystemClass FieldsSubsystemClass} (original type "fields_SubsystemClass"), type {@link us.kbase.cdmientityapi.FieldsIsSuperclassOf FieldsIsSuperclassOf} (original type "fields_IsSuperclassOf"), type {@link us.kbase.cdmientityapi.FieldsSubsystemClass FieldsSubsystemClass} (original type "fields_SubsystemClass")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSubsystemClass, FieldsIsSuperclassOf, FieldsSubsystemClass>> getRelationshipIsSuperclassOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSubsystemClass, FieldsIsSuperclassOf, FieldsSubsystemClass>>>> retType = new TypeReference<List<List<Tuple3<FieldsSubsystemClass, FieldsIsSuperclassOf, FieldsSubsystemClass>>>>() {};
        List<List<Tuple3<FieldsSubsystemClass, FieldsIsSuperclassOf, FieldsSubsystemClass>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsSuperclassOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsSubclassOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSubsystemClass FieldsSubsystemClass} (original type "fields_SubsystemClass"), type {@link us.kbase.cdmientityapi.FieldsIsSuperclassOf FieldsIsSuperclassOf} (original type "fields_IsSuperclassOf"), type {@link us.kbase.cdmientityapi.FieldsSubsystemClass FieldsSubsystemClass} (original type "fields_SubsystemClass")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSubsystemClass, FieldsIsSuperclassOf, FieldsSubsystemClass>> getRelationshipIsSubclassOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSubsystemClass, FieldsIsSuperclassOf, FieldsSubsystemClass>>>> retType = new TypeReference<List<List<Tuple3<FieldsSubsystemClass, FieldsIsSuperclassOf, FieldsSubsystemClass>>>>() {};
        List<List<Tuple3<FieldsSubsystemClass, FieldsIsSuperclassOf, FieldsSubsystemClass>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsSubclassOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsTaxonomyOf</p>
     * <pre>
     * A genome is assigned to a particular point in the taxonomy tree, but not
     * necessarily to a leaf node. In some cases, the exact species and strain is
     * not available when inserting the genome, so it is placed at the lowest node
     * that probably contains the actual genome.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTaxonomicGrouping FieldsTaxonomicGrouping} (original type "fields_TaxonomicGrouping"), type {@link us.kbase.cdmientityapi.FieldsIsTaxonomyOf FieldsIsTaxonomyOf} (original type "fields_IsTaxonomyOf"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTaxonomicGrouping, FieldsIsTaxonomyOf, FieldsGenome>> getRelationshipIsTaxonomyOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsTaxonomyOf, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsTaxonomyOf, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsTaxonomicGrouping, FieldsIsTaxonomyOf, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsTaxonomyOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsInTaxa</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsIsTaxonomyOf FieldsIsTaxonomyOf} (original type "fields_IsTaxonomyOf"), type {@link us.kbase.cdmientityapi.FieldsTaxonomicGrouping FieldsTaxonomicGrouping} (original type "fields_TaxonomicGrouping")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsIsTaxonomyOf, FieldsTaxonomicGrouping>> getRelationshipIsInTaxa(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsTaxonomyOf, FieldsTaxonomicGrouping>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsIsTaxonomyOf, FieldsTaxonomicGrouping>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsIsTaxonomyOf, FieldsTaxonomicGrouping>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsInTaxa", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsTerminusFor</p>
     * <pre>
     * A terminus for a scenario is a compound that acts as its
     * input or output. A compound can be the terminus for many scenarios,
     * and a scenario will have many termini. The relationship attributes
     * indicate whether the compound is an input to the scenario or an
     * output. In some cases, there may be multiple alternative output
     * groups. This is also indicated by the attributes.
     * It has the following fields:
     * =over 4
     * =item group_number
     * If zero, then the compound is an input. If one, the compound is an output. If two, the compound is an auxiliary output.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound"), type {@link us.kbase.cdmientityapi.FieldsIsTerminusFor FieldsIsTerminusFor} (original type "fields_IsTerminusFor"), type {@link us.kbase.cdmientityapi.FieldsScenario FieldsScenario} (original type "fields_Scenario")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompound, FieldsIsTerminusFor, FieldsScenario>> getRelationshipIsTerminusFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompound, FieldsIsTerminusFor, FieldsScenario>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompound, FieldsIsTerminusFor, FieldsScenario>>>>() {};
        List<List<Tuple3<FieldsCompound, FieldsIsTerminusFor, FieldsScenario>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsTerminusFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasAsTerminus</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsScenario FieldsScenario} (original type "fields_Scenario"), type {@link us.kbase.cdmientityapi.FieldsIsTerminusFor FieldsIsTerminusFor} (original type "fields_IsTerminusFor"), type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsScenario, FieldsIsTerminusFor, FieldsCompound>> getRelationshipHasAsTerminus(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsScenario, FieldsIsTerminusFor, FieldsCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsScenario, FieldsIsTerminusFor, FieldsCompound>>>>() {};
        List<List<Tuple3<FieldsScenario, FieldsIsTerminusFor, FieldsCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasAsTerminus", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsTriggeredBy</p>
     * <pre>
     * This connects a complex to the roles that work together to form the complex.
     * It has the following fields:
     * =over 4
     * =item optional
     * TRUE if the role is not necessarily required to trigger the complex, else FALSE
     * =item type
     * a string code that is used to determine whether a complex should be added to a model
     * =item triggering
     * TRUE if the presence of the role requires including the complex in the model, else FALSE
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsComplex FieldsComplex} (original type "fields_Complex"), type {@link us.kbase.cdmientityapi.FieldsIsTriggeredBy FieldsIsTriggeredBy} (original type "fields_IsTriggeredBy"), type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsComplex, FieldsIsTriggeredBy, FieldsRole>> getRelationshipIsTriggeredBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsComplex, FieldsIsTriggeredBy, FieldsRole>>>> retType = new TypeReference<List<List<Tuple3<FieldsComplex, FieldsIsTriggeredBy, FieldsRole>>>>() {};
        List<List<Tuple3<FieldsComplex, FieldsIsTriggeredBy, FieldsRole>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsTriggeredBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Triggers</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRole FieldsRole} (original type "fields_Role"), type {@link us.kbase.cdmientityapi.FieldsIsTriggeredBy FieldsIsTriggeredBy} (original type "fields_IsTriggeredBy"), type {@link us.kbase.cdmientityapi.FieldsComplex FieldsComplex} (original type "fields_Complex")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRole, FieldsIsTriggeredBy, FieldsComplex>> getRelationshipTriggers(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRole, FieldsIsTriggeredBy, FieldsComplex>>>> retType = new TypeReference<List<List<Tuple3<FieldsRole, FieldsIsTriggeredBy, FieldsComplex>>>>() {};
        List<List<Tuple3<FieldsRole, FieldsIsTriggeredBy, FieldsComplex>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Triggers", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsUsedToBuildTree</p>
     * <pre>
     * This relationship connects each tree to the alignment from
     * which it is built. There is at most one.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment"), type {@link us.kbase.cdmientityapi.FieldsIsUsedToBuildTree FieldsIsUsedToBuildTree} (original type "fields_IsUsedToBuildTree"), type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignment, FieldsIsUsedToBuildTree, FieldsTree>> getRelationshipIsUsedToBuildTree(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignment, FieldsIsUsedToBuildTree, FieldsTree>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignment, FieldsIsUsedToBuildTree, FieldsTree>>>>() {};
        List<List<Tuple3<FieldsAlignment, FieldsIsUsedToBuildTree, FieldsTree>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsUsedToBuildTree", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsBuiltFromAlignment</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree"), type {@link us.kbase.cdmientityapi.FieldsIsUsedToBuildTree FieldsIsUsedToBuildTree} (original type "fields_IsUsedToBuildTree"), type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTree, FieldsIsUsedToBuildTree, FieldsAlignment>> getRelationshipIsBuiltFromAlignment(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTree, FieldsIsUsedToBuildTree, FieldsAlignment>>>> retType = new TypeReference<List<List<Tuple3<FieldsTree, FieldsIsUsedToBuildTree, FieldsAlignment>>>>() {};
        List<List<Tuple3<FieldsTree, FieldsIsUsedToBuildTree, FieldsAlignment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsBuiltFromAlignment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Manages</p>
     * <pre>
     * This relationship connects a model to its associated biomass
     * composition reactions.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsModel FieldsModel} (original type "fields_Model"), type {@link us.kbase.cdmientityapi.FieldsManages FieldsManages} (original type "fields_Manages"), type {@link us.kbase.cdmientityapi.FieldsBiomass FieldsBiomass} (original type "fields_Biomass")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsModel, FieldsManages, FieldsBiomass>> getRelationshipManages(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsModel, FieldsManages, FieldsBiomass>>>> retType = new TypeReference<List<List<Tuple3<FieldsModel, FieldsManages, FieldsBiomass>>>>() {};
        List<List<Tuple3<FieldsModel, FieldsManages, FieldsBiomass>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Manages", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsManagedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsBiomass FieldsBiomass} (original type "fields_Biomass"), type {@link us.kbase.cdmientityapi.FieldsManages FieldsManages} (original type "fields_Manages"), type {@link us.kbase.cdmientityapi.FieldsModel FieldsModel} (original type "fields_Model")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsBiomass, FieldsManages, FieldsModel>> getRelationshipIsManagedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsBiomass, FieldsManages, FieldsModel>>>> retType = new TypeReference<List<List<Tuple3<FieldsBiomass, FieldsManages, FieldsModel>>>>() {};
        List<List<Tuple3<FieldsBiomass, FieldsManages, FieldsModel>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsManagedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_OntologyForSample</p>
     * <pre>
     * This relationship the ontology PO#, EO# or ENVO# associatioed with the sample.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsOntology FieldsOntology} (original type "fields_Ontology"), type {@link us.kbase.cdmientityapi.FieldsOntologyForSample FieldsOntologyForSample} (original type "fields_OntologyForSample"), type {@link us.kbase.cdmientityapi.FieldsSampleAnnotation FieldsSampleAnnotation} (original type "fields_SampleAnnotation")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsOntology, FieldsOntologyForSample, FieldsSampleAnnotation>> getRelationshipOntologyForSample(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsOntology, FieldsOntologyForSample, FieldsSampleAnnotation>>>> retType = new TypeReference<List<List<Tuple3<FieldsOntology, FieldsOntologyForSample, FieldsSampleAnnotation>>>>() {};
        List<List<Tuple3<FieldsOntology, FieldsOntologyForSample, FieldsSampleAnnotation>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_OntologyForSample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleHasOntology</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSampleAnnotation FieldsSampleAnnotation} (original type "fields_SampleAnnotation"), type {@link us.kbase.cdmientityapi.FieldsOntologyForSample FieldsOntologyForSample} (original type "fields_OntologyForSample"), type {@link us.kbase.cdmientityapi.FieldsOntology FieldsOntology} (original type "fields_Ontology")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSampleAnnotation, FieldsOntologyForSample, FieldsOntology>> getRelationshipSampleHasOntology(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSampleAnnotation, FieldsOntologyForSample, FieldsOntology>>>> retType = new TypeReference<List<List<Tuple3<FieldsSampleAnnotation, FieldsOntologyForSample, FieldsOntology>>>>() {};
        List<List<Tuple3<FieldsSampleAnnotation, FieldsOntologyForSample, FieldsOntology>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleHasOntology", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_OperatesIn</p>
     * <pre>
     * This relationship connects an experiment to the media in which the
     * experiment took place.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment"), type {@link us.kbase.cdmientityapi.FieldsOperatesIn FieldsOperatesIn} (original type "fields_OperatesIn"), type {@link us.kbase.cdmientityapi.FieldsMedia FieldsMedia} (original type "fields_Media")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperiment, FieldsOperatesIn, FieldsMedia>> getRelationshipOperatesIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperiment, FieldsOperatesIn, FieldsMedia>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperiment, FieldsOperatesIn, FieldsMedia>>>>() {};
        List<List<Tuple3<FieldsExperiment, FieldsOperatesIn, FieldsMedia>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_OperatesIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsUtilizedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsMedia FieldsMedia} (original type "fields_Media"), type {@link us.kbase.cdmientityapi.FieldsOperatesIn FieldsOperatesIn} (original type "fields_OperatesIn"), type {@link us.kbase.cdmientityapi.FieldsExperiment FieldsExperiment} (original type "fields_Experiment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsMedia, FieldsOperatesIn, FieldsExperiment>> getRelationshipIsUtilizedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsMedia, FieldsOperatesIn, FieldsExperiment>>>> retType = new TypeReference<List<List<Tuple3<FieldsMedia, FieldsOperatesIn, FieldsExperiment>>>>() {};
        List<List<Tuple3<FieldsMedia, FieldsOperatesIn, FieldsExperiment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsUtilizedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_OrdersExperimentalUnit</p>
     * <pre>
     * Experimental units may be ordered into time series. This
     * relationship describes which experimenal units belong to
     * which time series.
     * It has the following fields:
     * =over 4
     * =item time
     * The time at which the associated ExperimentUnit's data was taken.
     * =item timeMeta
     * Denotes that the associated ExperimentalUnit's data measures the time series as a whole - for example, lag and doubling times for bacterial growth curves.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTimeSeries FieldsTimeSeries} (original type "fields_TimeSeries"), type {@link us.kbase.cdmientityapi.FieldsOrdersExperimentalUnit FieldsOrdersExperimentalUnit} (original type "fields_OrdersExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTimeSeries, FieldsOrdersExperimentalUnit, FieldsExperimentalUnit>> getRelationshipOrdersExperimentalUnit(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTimeSeries, FieldsOrdersExperimentalUnit, FieldsExperimentalUnit>>>> retType = new TypeReference<List<List<Tuple3<FieldsTimeSeries, FieldsOrdersExperimentalUnit, FieldsExperimentalUnit>>>>() {};
        List<List<Tuple3<FieldsTimeSeries, FieldsOrdersExperimentalUnit, FieldsExperimentalUnit>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_OrdersExperimentalUnit", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsTimepointOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperimentalUnit FieldsExperimentalUnit} (original type "fields_ExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsOrdersExperimentalUnit FieldsOrdersExperimentalUnit} (original type "fields_OrdersExperimentalUnit"), type {@link us.kbase.cdmientityapi.FieldsTimeSeries FieldsTimeSeries} (original type "fields_TimeSeries")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperimentalUnit, FieldsOrdersExperimentalUnit, FieldsTimeSeries>> getRelationshipIsTimepointOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsOrdersExperimentalUnit, FieldsTimeSeries>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperimentalUnit, FieldsOrdersExperimentalUnit, FieldsTimeSeries>>>>() {};
        List<List<Tuple3<FieldsExperimentalUnit, FieldsOrdersExperimentalUnit, FieldsTimeSeries>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsTimepointOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Overlaps</p>
     * <pre>
     * A Scenario overlaps a diagram when the diagram displays a
     * portion of the reactions that make up the scenario. A scenario may
     * overlap many diagrams, and a diagram may be include portions of many
     * scenarios.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsScenario FieldsScenario} (original type "fields_Scenario"), type {@link us.kbase.cdmientityapi.FieldsOverlaps FieldsOverlaps} (original type "fields_Overlaps"), type {@link us.kbase.cdmientityapi.FieldsDiagram FieldsDiagram} (original type "fields_Diagram")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsScenario, FieldsOverlaps, FieldsDiagram>> getRelationshipOverlaps(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsScenario, FieldsOverlaps, FieldsDiagram>>>> retType = new TypeReference<List<List<Tuple3<FieldsScenario, FieldsOverlaps, FieldsDiagram>>>>() {};
        List<List<Tuple3<FieldsScenario, FieldsOverlaps, FieldsDiagram>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Overlaps", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IncludesPartOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsDiagram FieldsDiagram} (original type "fields_Diagram"), type {@link us.kbase.cdmientityapi.FieldsOverlaps FieldsOverlaps} (original type "fields_Overlaps"), type {@link us.kbase.cdmientityapi.FieldsScenario FieldsScenario} (original type "fields_Scenario")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsDiagram, FieldsOverlaps, FieldsScenario>> getRelationshipIncludesPartOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsDiagram, FieldsOverlaps, FieldsScenario>>>> retType = new TypeReference<List<List<Tuple3<FieldsDiagram, FieldsOverlaps, FieldsScenario>>>>() {};
        List<List<Tuple3<FieldsDiagram, FieldsOverlaps, FieldsScenario>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IncludesPartOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ParticipatesAs</p>
     * <pre>
     * This relationship connects a generic compound to a specific compound
     * where subceullar location has been specified.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound"), type {@link us.kbase.cdmientityapi.FieldsParticipatesAs FieldsParticipatesAs} (original type "fields_ParticipatesAs"), type {@link us.kbase.cdmientityapi.FieldsLocalizedCompound FieldsLocalizedCompound} (original type "fields_LocalizedCompound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompound, FieldsParticipatesAs, FieldsLocalizedCompound>> getRelationshipParticipatesAs(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompound, FieldsParticipatesAs, FieldsLocalizedCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompound, FieldsParticipatesAs, FieldsLocalizedCompound>>>>() {};
        List<List<Tuple3<FieldsCompound, FieldsParticipatesAs, FieldsLocalizedCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ParticipatesAs", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsParticipationOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsLocalizedCompound FieldsLocalizedCompound} (original type "fields_LocalizedCompound"), type {@link us.kbase.cdmientityapi.FieldsParticipatesAs FieldsParticipatesAs} (original type "fields_ParticipatesAs"), type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsLocalizedCompound, FieldsParticipatesAs, FieldsCompound>> getRelationshipIsParticipationOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsLocalizedCompound, FieldsParticipatesAs, FieldsCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsLocalizedCompound, FieldsParticipatesAs, FieldsCompound>>>>() {};
        List<List<Tuple3<FieldsLocalizedCompound, FieldsParticipatesAs, FieldsCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsParticipationOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_PerformedExperiment</p>
     * <pre>
     * Denotes that a Person was associated with a
     * Experiment in some role.
     * It has the following fields:
     * =over 4
     * =item role
     * Describes the role the person played in the experiment. Examples are Primary Investigator, Designer, Experimentalist, etc.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPerson FieldsPerson} (original type "fields_Person"), type {@link us.kbase.cdmientityapi.FieldsPerformedExperiment FieldsPerformedExperiment} (original type "fields_PerformedExperiment"), type {@link us.kbase.cdmientityapi.FieldsExperimentMeta FieldsExperimentMeta} (original type "fields_ExperimentMeta")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPerson, FieldsPerformedExperiment, FieldsExperimentMeta>> getRelationshipPerformedExperiment(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPerson, FieldsPerformedExperiment, FieldsExperimentMeta>>>> retType = new TypeReference<List<List<Tuple3<FieldsPerson, FieldsPerformedExperiment, FieldsExperimentMeta>>>>() {};
        List<List<Tuple3<FieldsPerson, FieldsPerformedExperiment, FieldsExperimentMeta>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_PerformedExperiment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_PerformedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperimentMeta FieldsExperimentMeta} (original type "fields_ExperimentMeta"), type {@link us.kbase.cdmientityapi.FieldsPerformedExperiment FieldsPerformedExperiment} (original type "fields_PerformedExperiment"), type {@link us.kbase.cdmientityapi.FieldsPerson FieldsPerson} (original type "fields_Person")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperimentMeta, FieldsPerformedExperiment, FieldsPerson>> getRelationshipPerformedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperimentMeta, FieldsPerformedExperiment, FieldsPerson>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperimentMeta, FieldsPerformedExperiment, FieldsPerson>>>>() {};
        List<List<Tuple3<FieldsExperimentMeta, FieldsPerformedExperiment, FieldsPerson>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_PerformedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_PersonAnnotatedSample</p>
     * <pre>
     * Only stores a person if a person annotates the data by hand.  
     * Automated Sample Annotations will not be annotated by a person.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPerson FieldsPerson} (original type "fields_Person"), type {@link us.kbase.cdmientityapi.FieldsPersonAnnotatedSample FieldsPersonAnnotatedSample} (original type "fields_PersonAnnotatedSample"), type {@link us.kbase.cdmientityapi.FieldsSampleAnnotation FieldsSampleAnnotation} (original type "fields_SampleAnnotation")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPerson, FieldsPersonAnnotatedSample, FieldsSampleAnnotation>> getRelationshipPersonAnnotatedSample(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPerson, FieldsPersonAnnotatedSample, FieldsSampleAnnotation>>>> retType = new TypeReference<List<List<Tuple3<FieldsPerson, FieldsPersonAnnotatedSample, FieldsSampleAnnotation>>>>() {};
        List<List<Tuple3<FieldsPerson, FieldsPersonAnnotatedSample, FieldsSampleAnnotation>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_PersonAnnotatedSample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleAnnotatedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSampleAnnotation FieldsSampleAnnotation} (original type "fields_SampleAnnotation"), type {@link us.kbase.cdmientityapi.FieldsPersonAnnotatedSample FieldsPersonAnnotatedSample} (original type "fields_PersonAnnotatedSample"), type {@link us.kbase.cdmientityapi.FieldsPerson FieldsPerson} (original type "fields_Person")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSampleAnnotation, FieldsPersonAnnotatedSample, FieldsPerson>> getRelationshipSampleAnnotatedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSampleAnnotation, FieldsPersonAnnotatedSample, FieldsPerson>>>> retType = new TypeReference<List<List<Tuple3<FieldsSampleAnnotation, FieldsPersonAnnotatedSample, FieldsPerson>>>>() {};
        List<List<Tuple3<FieldsSampleAnnotation, FieldsPersonAnnotatedSample, FieldsPerson>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleAnnotatedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_PlatformWithSamples</p>
     * <pre>
     * This relationship indicates the expression samples that were run on a particular platform.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPlatform FieldsPlatform} (original type "fields_Platform"), type {@link us.kbase.cdmientityapi.FieldsPlatformWithSamples FieldsPlatformWithSamples} (original type "fields_PlatformWithSamples"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPlatform, FieldsPlatformWithSamples, FieldsSample>> getRelationshipPlatformWithSamples(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPlatform, FieldsPlatformWithSamples, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsPlatform, FieldsPlatformWithSamples, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsPlatform, FieldsPlatformWithSamples, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_PlatformWithSamples", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleRunOnPlatform</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsPlatformWithSamples FieldsPlatformWithSamples} (original type "fields_PlatformWithSamples"), type {@link us.kbase.cdmientityapi.FieldsPlatform FieldsPlatform} (original type "fields_Platform")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsPlatformWithSamples, FieldsPlatform>> getRelationshipSampleRunOnPlatform(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsPlatformWithSamples, FieldsPlatform>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsPlatformWithSamples, FieldsPlatform>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsPlatformWithSamples, FieldsPlatform>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleRunOnPlatform", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ProducedResultsFor</p>
     * <pre>
     * This relationship connects a probe set to a genome for which it was
     * used to produce experimental results. In general, a probe set is used for
     * only one genome and vice versa, but this is not a requirement.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProbeSet FieldsProbeSet} (original type "fields_ProbeSet"), type {@link us.kbase.cdmientityapi.FieldsProducedResultsFor FieldsProducedResultsFor} (original type "fields_ProducedResultsFor"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProbeSet, FieldsProducedResultsFor, FieldsGenome>> getRelationshipProducedResultsFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProbeSet, FieldsProducedResultsFor, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsProbeSet, FieldsProducedResultsFor, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsProbeSet, FieldsProducedResultsFor, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ProducedResultsFor", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HadResultsProducedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsProducedResultsFor FieldsProducedResultsFor} (original type "fields_ProducedResultsFor"), type {@link us.kbase.cdmientityapi.FieldsProbeSet FieldsProbeSet} (original type "fields_ProbeSet")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsProducedResultsFor, FieldsProbeSet>> getRelationshipHadResultsProducedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsProducedResultsFor, FieldsProbeSet>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsProducedResultsFor, FieldsProbeSet>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsProducedResultsFor, FieldsProbeSet>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HadResultsProducedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ProtocolForSample</p>
     * <pre>
     * This relationship indicates the protocol used in the expression sample.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProtocol FieldsProtocol} (original type "fields_Protocol"), type {@link us.kbase.cdmientityapi.FieldsProtocolForSample FieldsProtocolForSample} (original type "fields_ProtocolForSample"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProtocol, FieldsProtocolForSample, FieldsSample>> getRelationshipProtocolForSample(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProtocol, FieldsProtocolForSample, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsProtocol, FieldsProtocolForSample, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsProtocol, FieldsProtocolForSample, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ProtocolForSample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleUsesProtocol</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsProtocolForSample FieldsProtocolForSample} (original type "fields_ProtocolForSample"), type {@link us.kbase.cdmientityapi.FieldsProtocol FieldsProtocol} (original type "fields_Protocol")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsProtocolForSample, FieldsProtocol>> getRelationshipSampleUsesProtocol(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsProtocolForSample, FieldsProtocol>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsProtocolForSample, FieldsProtocol>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsProtocolForSample, FieldsProtocol>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleUsesProtocol", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Provided</p>
     * <pre>
     * This relationship connects a source (core) database
     * to the subsystems it submitted to the knowledge base.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source"), type {@link us.kbase.cdmientityapi.FieldsProvided FieldsProvided} (original type "fields_Provided"), type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSource, FieldsProvided, FieldsSubsystem>> getRelationshipProvided(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSource, FieldsProvided, FieldsSubsystem>>>> retType = new TypeReference<List<List<Tuple3<FieldsSource, FieldsProvided, FieldsSubsystem>>>>() {};
        List<List<Tuple3<FieldsSource, FieldsProvided, FieldsSubsystem>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Provided", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_WasProvidedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSubsystem FieldsSubsystem} (original type "fields_Subsystem"), type {@link us.kbase.cdmientityapi.FieldsProvided FieldsProvided} (original type "fields_Provided"), type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSubsystem, FieldsProvided, FieldsSource>> getRelationshipWasProvidedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsProvided, FieldsSource>>>> retType = new TypeReference<List<List<Tuple3<FieldsSubsystem, FieldsProvided, FieldsSource>>>>() {};
        List<List<Tuple3<FieldsSubsystem, FieldsProvided, FieldsSource>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_WasProvidedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_PublishedAssociation</p>
     * <pre>
     * The PublishedAssociation relationship links associations
     * to the manuscript they are published in.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication"), type {@link us.kbase.cdmientityapi.FieldsPublishedAssociation FieldsPublishedAssociation} (original type "fields_PublishedAssociation"), type {@link us.kbase.cdmientityapi.FieldsAssociation FieldsAssociation} (original type "fields_Association")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPublication, FieldsPublishedAssociation, FieldsAssociation>> getRelationshipPublishedAssociation(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPublication, FieldsPublishedAssociation, FieldsAssociation>>>> retType = new TypeReference<List<List<Tuple3<FieldsPublication, FieldsPublishedAssociation, FieldsAssociation>>>>() {};
        List<List<Tuple3<FieldsPublication, FieldsPublishedAssociation, FieldsAssociation>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_PublishedAssociation", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_AssociationPublishedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAssociation FieldsAssociation} (original type "fields_Association"), type {@link us.kbase.cdmientityapi.FieldsPublishedAssociation FieldsPublishedAssociation} (original type "fields_PublishedAssociation"), type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAssociation, FieldsPublishedAssociation, FieldsPublication>> getRelationshipAssociationPublishedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAssociation, FieldsPublishedAssociation, FieldsPublication>>>> retType = new TypeReference<List<List<Tuple3<FieldsAssociation, FieldsPublishedAssociation, FieldsPublication>>>>() {};
        List<List<Tuple3<FieldsAssociation, FieldsPublishedAssociation, FieldsPublication>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_AssociationPublishedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_PublishedExperiment</p>
     * <pre>
     * The PublishedExperiment relationship describes where a
     * particular experiment was published.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication"), type {@link us.kbase.cdmientityapi.FieldsPublishedExperiment FieldsPublishedExperiment} (original type "fields_PublishedExperiment"), type {@link us.kbase.cdmientityapi.FieldsExperimentMeta FieldsExperimentMeta} (original type "fields_ExperimentMeta")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPublication, FieldsPublishedExperiment, FieldsExperimentMeta>> getRelationshipPublishedExperiment(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPublication, FieldsPublishedExperiment, FieldsExperimentMeta>>>> retType = new TypeReference<List<List<Tuple3<FieldsPublication, FieldsPublishedExperiment, FieldsExperimentMeta>>>>() {};
        List<List<Tuple3<FieldsPublication, FieldsPublishedExperiment, FieldsExperimentMeta>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_PublishedExperiment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ExperimentPublishedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsExperimentMeta FieldsExperimentMeta} (original type "fields_ExperimentMeta"), type {@link us.kbase.cdmientityapi.FieldsPublishedExperiment FieldsPublishedExperiment} (original type "fields_PublishedExperiment"), type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsExperimentMeta, FieldsPublishedExperiment, FieldsPublication>> getRelationshipExperimentPublishedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsExperimentMeta, FieldsPublishedExperiment, FieldsPublication>>>> retType = new TypeReference<List<List<Tuple3<FieldsExperimentMeta, FieldsPublishedExperiment, FieldsPublication>>>>() {};
        List<List<Tuple3<FieldsExperimentMeta, FieldsPublishedExperiment, FieldsPublication>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ExperimentPublishedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_PublishedProtocol</p>
     * <pre>
     * The ProtocolPublishedIn relationship describes where a
     * particular protocol was published.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication"), type {@link us.kbase.cdmientityapi.FieldsPublishedProtocol FieldsPublishedProtocol} (original type "fields_PublishedProtocol"), type {@link us.kbase.cdmientityapi.FieldsProtocol FieldsProtocol} (original type "fields_Protocol")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPublication, FieldsPublishedProtocol, FieldsProtocol>> getRelationshipPublishedProtocol(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPublication, FieldsPublishedProtocol, FieldsProtocol>>>> retType = new TypeReference<List<List<Tuple3<FieldsPublication, FieldsPublishedProtocol, FieldsProtocol>>>>() {};
        List<List<Tuple3<FieldsPublication, FieldsPublishedProtocol, FieldsProtocol>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_PublishedProtocol", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ProtocolPublishedIn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsProtocol FieldsProtocol} (original type "fields_Protocol"), type {@link us.kbase.cdmientityapi.FieldsPublishedProtocol FieldsPublishedProtocol} (original type "fields_PublishedProtocol"), type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsProtocol, FieldsPublishedProtocol, FieldsPublication>> getRelationshipProtocolPublishedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsProtocol, FieldsPublishedProtocol, FieldsPublication>>>> retType = new TypeReference<List<List<Tuple3<FieldsProtocol, FieldsPublishedProtocol, FieldsPublication>>>>() {};
        List<List<Tuple3<FieldsProtocol, FieldsPublishedProtocol, FieldsPublication>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ProtocolPublishedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_RegulogHasRegulon</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulog FieldsRegulog} (original type "fields_Regulog"), type {@link us.kbase.cdmientityapi.FieldsRegulogHasRegulon FieldsRegulogHasRegulon} (original type "fields_RegulogHasRegulon"), type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulog, FieldsRegulogHasRegulon, FieldsRegulon>> getRelationshipRegulogHasRegulon(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulog, FieldsRegulogHasRegulon, FieldsRegulon>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulog, FieldsRegulogHasRegulon, FieldsRegulon>>>>() {};
        List<List<Tuple3<FieldsRegulog, FieldsRegulogHasRegulon, FieldsRegulon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_RegulogHasRegulon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_RegulonIsInRegolog</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon"), type {@link us.kbase.cdmientityapi.FieldsRegulogHasRegulon FieldsRegulogHasRegulon} (original type "fields_RegulogHasRegulon"), type {@link us.kbase.cdmientityapi.FieldsRegulog FieldsRegulog} (original type "fields_Regulog")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulon, FieldsRegulogHasRegulon, FieldsRegulog>> getRelationshipRegulonIsInRegolog(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulon, FieldsRegulogHasRegulon, FieldsRegulog>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulon, FieldsRegulogHasRegulon, FieldsRegulog>>>>() {};
        List<List<Tuple3<FieldsRegulon, FieldsRegulogHasRegulon, FieldsRegulog>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_RegulonIsInRegolog", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_RegulomeHasGenome</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulome FieldsRegulome} (original type "fields_Regulome"), type {@link us.kbase.cdmientityapi.FieldsRegulomeHasGenome FieldsRegulomeHasGenome} (original type "fields_RegulomeHasGenome"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulome, FieldsRegulomeHasGenome, FieldsGenome>> getRelationshipRegulomeHasGenome(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulome, FieldsRegulomeHasGenome, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulome, FieldsRegulomeHasGenome, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsRegulome, FieldsRegulomeHasGenome, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_RegulomeHasGenome", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_GenomeIsInRegulome</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsRegulomeHasGenome FieldsRegulomeHasGenome} (original type "fields_RegulomeHasGenome"), type {@link us.kbase.cdmientityapi.FieldsRegulome FieldsRegulome} (original type "fields_Regulome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsRegulomeHasGenome, FieldsRegulome>> getRelationshipGenomeIsInRegulome(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsRegulomeHasGenome, FieldsRegulome>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsRegulomeHasGenome, FieldsRegulome>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsRegulomeHasGenome, FieldsRegulome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_GenomeIsInRegulome", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_RegulomeHasRegulon</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulome FieldsRegulome} (original type "fields_Regulome"), type {@link us.kbase.cdmientityapi.FieldsRegulomeHasRegulon FieldsRegulomeHasRegulon} (original type "fields_RegulomeHasRegulon"), type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulome, FieldsRegulomeHasRegulon, FieldsRegulon>> getRelationshipRegulomeHasRegulon(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulome, FieldsRegulomeHasRegulon, FieldsRegulon>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulome, FieldsRegulomeHasRegulon, FieldsRegulon>>>>() {};
        List<List<Tuple3<FieldsRegulome, FieldsRegulomeHasRegulon, FieldsRegulon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_RegulomeHasRegulon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_RegulonIsInRegolome</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon"), type {@link us.kbase.cdmientityapi.FieldsRegulomeHasRegulon FieldsRegulomeHasRegulon} (original type "fields_RegulomeHasRegulon"), type {@link us.kbase.cdmientityapi.FieldsRegulome FieldsRegulome} (original type "fields_Regulome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulon, FieldsRegulomeHasRegulon, FieldsRegulome>> getRelationshipRegulonIsInRegolome(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulon, FieldsRegulomeHasRegulon, FieldsRegulome>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulon, FieldsRegulomeHasRegulon, FieldsRegulome>>>>() {};
        List<List<Tuple3<FieldsRegulon, FieldsRegulomeHasRegulon, FieldsRegulome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_RegulonIsInRegolome", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_RegulomeSource</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulome FieldsRegulome} (original type "fields_Regulome"), type {@link us.kbase.cdmientityapi.FieldsRegulomeSource FieldsRegulomeSource} (original type "fields_RegulomeSource"), type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulome, FieldsRegulomeSource, FieldsSource>> getRelationshipRegulomeSource(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulome, FieldsRegulomeSource, FieldsSource>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulome, FieldsRegulomeSource, FieldsSource>>>>() {};
        List<List<Tuple3<FieldsRegulome, FieldsRegulomeSource, FieldsSource>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_RegulomeSource", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_CreatedRegulome</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source"), type {@link us.kbase.cdmientityapi.FieldsRegulomeSource FieldsRegulomeSource} (original type "fields_RegulomeSource"), type {@link us.kbase.cdmientityapi.FieldsRegulome FieldsRegulome} (original type "fields_Regulome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSource, FieldsRegulomeSource, FieldsRegulome>> getRelationshipCreatedRegulome(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSource, FieldsRegulomeSource, FieldsRegulome>>>> retType = new TypeReference<List<List<Tuple3<FieldsSource, FieldsRegulomeSource, FieldsRegulome>>>>() {};
        List<List<Tuple3<FieldsSource, FieldsRegulomeSource, FieldsRegulome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_CreatedRegulome", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_RegulonHasOperon</p>
     * <pre>
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon"), type {@link us.kbase.cdmientityapi.FieldsRegulonHasOperon FieldsRegulonHasOperon} (original type "fields_RegulonHasOperon"), type {@link us.kbase.cdmientityapi.FieldsOperon FieldsOperon} (original type "fields_Operon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsRegulon, FieldsRegulonHasOperon, FieldsOperon>> getRelationshipRegulonHasOperon(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsRegulon, FieldsRegulonHasOperon, FieldsOperon>>>> retType = new TypeReference<List<List<Tuple3<FieldsRegulon, FieldsRegulonHasOperon, FieldsOperon>>>>() {};
        List<List<Tuple3<FieldsRegulon, FieldsRegulonHasOperon, FieldsOperon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_RegulonHasOperon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_OperonIsInRegulon</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsOperon FieldsOperon} (original type "fields_Operon"), type {@link us.kbase.cdmientityapi.FieldsRegulonHasOperon FieldsRegulonHasOperon} (original type "fields_RegulonHasOperon"), type {@link us.kbase.cdmientityapi.FieldsRegulon FieldsRegulon} (original type "fields_Regulon")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsOperon, FieldsRegulonHasOperon, FieldsRegulon>> getRelationshipOperonIsInRegulon(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsOperon, FieldsRegulonHasOperon, FieldsRegulon>>>> retType = new TypeReference<List<List<Tuple3<FieldsOperon, FieldsRegulonHasOperon, FieldsRegulon>>>>() {};
        List<List<Tuple3<FieldsOperon, FieldsRegulonHasOperon, FieldsRegulon>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_OperonIsInRegulon", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleAveragedFrom</p>
     * <pre>
     * Custom averaging of samples (typically replicates).
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsSampleAveragedFrom FieldsSampleAveragedFrom} (original type "fields_SampleAveragedFrom"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsSampleAveragedFrom, FieldsSample>> getRelationshipSampleAveragedFrom(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleAveragedFrom, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleAveragedFrom, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsSampleAveragedFrom, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleAveragedFrom", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleComponentOf</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsSampleAveragedFrom FieldsSampleAveragedFrom} (original type "fields_SampleAveragedFrom"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsSampleAveragedFrom, FieldsSample>> getRelationshipSampleComponentOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleAveragedFrom, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleAveragedFrom, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsSampleAveragedFrom, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleComponentOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleContactPerson</p>
     * <pre>
     * The people that performed the expression experiment(sample).
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsSampleContactPerson FieldsSampleContactPerson} (original type "fields_SampleContactPerson"), type {@link us.kbase.cdmientityapi.FieldsPerson FieldsPerson} (original type "fields_Person")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsSampleContactPerson, FieldsPerson>> getRelationshipSampleContactPerson(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleContactPerson, FieldsPerson>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleContactPerson, FieldsPerson>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsSampleContactPerson, FieldsPerson>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleContactPerson", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_PersonPerformedSample</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPerson FieldsPerson} (original type "fields_Person"), type {@link us.kbase.cdmientityapi.FieldsSampleContactPerson FieldsSampleContactPerson} (original type "fields_SampleContactPerson"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPerson, FieldsSampleContactPerson, FieldsSample>> getRelationshipPersonPerformedSample(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPerson, FieldsSampleContactPerson, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsPerson, FieldsSampleContactPerson, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsPerson, FieldsSampleContactPerson, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_PersonPerformedSample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleHasAnnotations</p>
     * <pre>
     * This relationship indicates the sample annotations that belong to the sample.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsSampleHasAnnotations FieldsSampleHasAnnotations} (original type "fields_SampleHasAnnotations"), type {@link us.kbase.cdmientityapi.FieldsSampleAnnotation FieldsSampleAnnotation} (original type "fields_SampleAnnotation")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsSampleHasAnnotations, FieldsSampleAnnotation>> getRelationshipSampleHasAnnotations(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleHasAnnotations, FieldsSampleAnnotation>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleHasAnnotations, FieldsSampleAnnotation>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsSampleHasAnnotations, FieldsSampleAnnotation>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleHasAnnotations", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_AnnotationsForSample</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSampleAnnotation FieldsSampleAnnotation} (original type "fields_SampleAnnotation"), type {@link us.kbase.cdmientityapi.FieldsSampleHasAnnotations FieldsSampleHasAnnotations} (original type "fields_SampleHasAnnotations"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSampleAnnotation, FieldsSampleHasAnnotations, FieldsSample>> getRelationshipAnnotationsForSample(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSampleAnnotation, FieldsSampleHasAnnotations, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsSampleAnnotation, FieldsSampleHasAnnotations, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsSampleAnnotation, FieldsSampleHasAnnotations, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_AnnotationsForSample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleInSeries</p>
     * <pre>
     * This relationship indicates what samples are in a series.  Note a sample can be in more than one series.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsSampleInSeries FieldsSampleInSeries} (original type "fields_SampleInSeries"), type {@link us.kbase.cdmientityapi.FieldsSeries FieldsSeries} (original type "fields_Series")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsSampleInSeries, FieldsSeries>> getRelationshipSampleInSeries(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleInSeries, FieldsSeries>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleInSeries, FieldsSeries>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsSampleInSeries, FieldsSeries>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleInSeries", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SeriesWithSamples</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSeries FieldsSeries} (original type "fields_Series"), type {@link us.kbase.cdmientityapi.FieldsSampleInSeries FieldsSampleInSeries} (original type "fields_SampleInSeries"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSeries, FieldsSampleInSeries, FieldsSample>> getRelationshipSeriesWithSamples(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSeries, FieldsSampleInSeries, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsSeries, FieldsSampleInSeries, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsSeries, FieldsSampleInSeries, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SeriesWithSamples", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleMeasurements</p>
     * <pre>
     * The Measurements for expression microarray data should be in Log2 space and 
     * the measurements for a given sample should have the median set to zero.  RNA-Seq data will likely be in FPKM.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsSampleMeasurements FieldsSampleMeasurements} (original type "fields_SampleMeasurements"), type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsSampleMeasurements, FieldsMeasurement>> getRelationshipSampleMeasurements(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleMeasurements, FieldsMeasurement>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsSampleMeasurements, FieldsMeasurement>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsSampleMeasurements, FieldsMeasurement>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleMeasurements", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_MeasurementInSample</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsMeasurement FieldsMeasurement} (original type "fields_Measurement"), type {@link us.kbase.cdmientityapi.FieldsSampleMeasurements FieldsSampleMeasurements} (original type "fields_SampleMeasurements"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsMeasurement, FieldsSampleMeasurements, FieldsSample>> getRelationshipMeasurementInSample(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsSampleMeasurements, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsMeasurement, FieldsSampleMeasurements, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsMeasurement, FieldsSampleMeasurements, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_MeasurementInSample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SamplesInReplicateGroup</p>
     * <pre>
     * The samples that are identified as being part of one replicate group.  All samples in replicate group are replicates of one another.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsSamplesInReplicateGroup FieldsSamplesInReplicateGroup} (original type "fields_SamplesInReplicateGroup"), type {@link us.kbase.cdmientityapi.FieldsReplicateGroup FieldsReplicateGroup} (original type "fields_ReplicateGroup")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsSamplesInReplicateGroup, FieldsReplicateGroup>> getRelationshipSamplesInReplicateGroup(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsSamplesInReplicateGroup, FieldsReplicateGroup>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsSamplesInReplicateGroup, FieldsReplicateGroup>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsSamplesInReplicateGroup, FieldsReplicateGroup>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SamplesInReplicateGroup", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_ReplicateGroupsForSample</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsReplicateGroup FieldsReplicateGroup} (original type "fields_ReplicateGroup"), type {@link us.kbase.cdmientityapi.FieldsSamplesInReplicateGroup FieldsSamplesInReplicateGroup} (original type "fields_SamplesInReplicateGroup"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsReplicateGroup, FieldsSamplesInReplicateGroup, FieldsSample>> getRelationshipReplicateGroupsForSample(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsReplicateGroup, FieldsSamplesInReplicateGroup, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsReplicateGroup, FieldsSamplesInReplicateGroup, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsReplicateGroup, FieldsSamplesInReplicateGroup, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_ReplicateGroupsForSample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SeriesPublishedIn</p>
     * <pre>
     * This relationship indicates where the series was published.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSeries FieldsSeries} (original type "fields_Series"), type {@link us.kbase.cdmientityapi.FieldsSeriesPublishedIn FieldsSeriesPublishedIn} (original type "fields_SeriesPublishedIn"), type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSeries, FieldsSeriesPublishedIn, FieldsPublication>> getRelationshipSeriesPublishedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSeries, FieldsSeriesPublishedIn, FieldsPublication>>>> retType = new TypeReference<List<List<Tuple3<FieldsSeries, FieldsSeriesPublishedIn, FieldsPublication>>>>() {};
        List<List<Tuple3<FieldsSeries, FieldsSeriesPublishedIn, FieldsPublication>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SeriesPublishedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_PublicationsForSeries</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPublication FieldsPublication} (original type "fields_Publication"), type {@link us.kbase.cdmientityapi.FieldsSeriesPublishedIn FieldsSeriesPublishedIn} (original type "fields_SeriesPublishedIn"), type {@link us.kbase.cdmientityapi.FieldsSeries FieldsSeries} (original type "fields_Series")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPublication, FieldsSeriesPublishedIn, FieldsSeries>> getRelationshipPublicationsForSeries(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPublication, FieldsSeriesPublishedIn, FieldsSeries>>>> retType = new TypeReference<List<List<Tuple3<FieldsPublication, FieldsSeriesPublishedIn, FieldsSeries>>>>() {};
        List<List<Tuple3<FieldsPublication, FieldsSeriesPublishedIn, FieldsSeries>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_PublicationsForSeries", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Shows</p>
     * <pre>
     * This relationship indicates that a compound appears on a
     * particular diagram. The same compound can appear on many diagrams,
     * and a diagram always contains many compounds.
     * It has the following fields:
     * =over 4
     * =item location
     * Location of the compound's node on the diagram.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsDiagram FieldsDiagram} (original type "fields_Diagram"), type {@link us.kbase.cdmientityapi.FieldsShows FieldsShows} (original type "fields_Shows"), type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsDiagram, FieldsShows, FieldsCompound>> getRelationshipShows(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsDiagram, FieldsShows, FieldsCompound>>>> retType = new TypeReference<List<List<Tuple3<FieldsDiagram, FieldsShows, FieldsCompound>>>>() {};
        List<List<Tuple3<FieldsDiagram, FieldsShows, FieldsCompound>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Shows", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsShownOn</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCompound FieldsCompound} (original type "fields_Compound"), type {@link us.kbase.cdmientityapi.FieldsShows FieldsShows} (original type "fields_Shows"), type {@link us.kbase.cdmientityapi.FieldsDiagram FieldsDiagram} (original type "fields_Diagram")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCompound, FieldsShows, FieldsDiagram>> getRelationshipIsShownOn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCompound, FieldsShows, FieldsDiagram>>>> retType = new TypeReference<List<List<Tuple3<FieldsCompound, FieldsShows, FieldsDiagram>>>>() {};
        List<List<Tuple3<FieldsCompound, FieldsShows, FieldsDiagram>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsShownOn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_StrainParentOf</p>
     * <pre>
     * The recursive StrainParentOf relationship organizes derived
     * organisms into a tree based on parent/child relationships.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain"), type {@link us.kbase.cdmientityapi.FieldsStrainParentOf FieldsStrainParentOf} (original type "fields_StrainParentOf"), type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsStrain, FieldsStrainParentOf, FieldsStrain>> getRelationshipStrainParentOf(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsStrain, FieldsStrainParentOf, FieldsStrain>>>> retType = new TypeReference<List<List<Tuple3<FieldsStrain, FieldsStrainParentOf, FieldsStrain>>>>() {};
        List<List<Tuple3<FieldsStrain, FieldsStrainParentOf, FieldsStrain>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_StrainParentOf", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_DerivedFromStrain</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain"), type {@link us.kbase.cdmientityapi.FieldsStrainParentOf FieldsStrainParentOf} (original type "fields_StrainParentOf"), type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsStrain, FieldsStrainParentOf, FieldsStrain>> getRelationshipDerivedFromStrain(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsStrain, FieldsStrainParentOf, FieldsStrain>>>> retType = new TypeReference<List<List<Tuple3<FieldsStrain, FieldsStrainParentOf, FieldsStrain>>>>() {};
        List<List<Tuple3<FieldsStrain, FieldsStrainParentOf, FieldsStrain>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_DerivedFromStrain", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_StrainWithPlatforms</p>
     * <pre>
     * This relationship indicates the platforms for a strain.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain"), type {@link us.kbase.cdmientityapi.FieldsStrainWithPlatforms FieldsStrainWithPlatforms} (original type "fields_StrainWithPlatforms"), type {@link us.kbase.cdmientityapi.FieldsPlatform FieldsPlatform} (original type "fields_Platform")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsStrain, FieldsStrainWithPlatforms, FieldsPlatform>> getRelationshipStrainWithPlatforms(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsStrain, FieldsStrainWithPlatforms, FieldsPlatform>>>> retType = new TypeReference<List<List<Tuple3<FieldsStrain, FieldsStrainWithPlatforms, FieldsPlatform>>>>() {};
        List<List<Tuple3<FieldsStrain, FieldsStrainWithPlatforms, FieldsPlatform>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_StrainWithPlatforms", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_PlatformForStrain</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsPlatform FieldsPlatform} (original type "fields_Platform"), type {@link us.kbase.cdmientityapi.FieldsStrainWithPlatforms FieldsStrainWithPlatforms} (original type "fields_StrainWithPlatforms"), type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsPlatform, FieldsStrainWithPlatforms, FieldsStrain>> getRelationshipPlatformForStrain(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsPlatform, FieldsStrainWithPlatforms, FieldsStrain>>>> retType = new TypeReference<List<List<Tuple3<FieldsPlatform, FieldsStrainWithPlatforms, FieldsStrain>>>>() {};
        List<List<Tuple3<FieldsPlatform, FieldsStrainWithPlatforms, FieldsStrain>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_PlatformForStrain", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_StrainWithSample</p>
     * <pre>
     * This indicates which expression samples a strain has.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain"), type {@link us.kbase.cdmientityapi.FieldsStrainWithSample FieldsStrainWithSample} (original type "fields_StrainWithSample"), type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsStrain, FieldsStrainWithSample, FieldsSample>> getRelationshipStrainWithSample(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsStrain, FieldsStrainWithSample, FieldsSample>>>> retType = new TypeReference<List<List<Tuple3<FieldsStrain, FieldsStrainWithSample, FieldsSample>>>>() {};
        List<List<Tuple3<FieldsStrain, FieldsStrainWithSample, FieldsSample>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_StrainWithSample", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SampleForStrain</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSample FieldsSample} (original type "fields_Sample"), type {@link us.kbase.cdmientityapi.FieldsStrainWithSample FieldsStrainWithSample} (original type "fields_StrainWithSample"), type {@link us.kbase.cdmientityapi.FieldsStrain FieldsStrain} (original type "fields_Strain")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSample, FieldsStrainWithSample, FieldsStrain>> getRelationshipSampleForStrain(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSample, FieldsStrainWithSample, FieldsStrain>>>> retType = new TypeReference<List<List<Tuple3<FieldsSample, FieldsStrainWithSample, FieldsStrain>>>>() {};
        List<List<Tuple3<FieldsSample, FieldsStrainWithSample, FieldsStrain>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SampleForStrain", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Submitted</p>
     * <pre>
     * This relationship connects a genome to the
     * core database from which it was loaded.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source"), type {@link us.kbase.cdmientityapi.FieldsSubmitted FieldsSubmitted} (original type "fields_Submitted"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSource, FieldsSubmitted, FieldsGenome>> getRelationshipSubmitted(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSource, FieldsSubmitted, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsSource, FieldsSubmitted, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsSource, FieldsSubmitted, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Submitted", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_WasSubmittedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsSubmitted FieldsSubmitted} (original type "fields_Submitted"), type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsSubmitted, FieldsSource>> getRelationshipWasSubmittedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsSubmitted, FieldsSource>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsSubmitted, FieldsSource>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsSubmitted, FieldsSource>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_WasSubmittedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SupersedesAlignment</p>
     * <pre>
     * This relationship connects an alignment to the alignments
     * it replaces.
     * It has the following fields:
     * =over 4
     * =item successor_type
     * Indicates whether sequences were removed or added to create the new alignment.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment"), type {@link us.kbase.cdmientityapi.FieldsSupersedesAlignment FieldsSupersedesAlignment} (original type "fields_SupersedesAlignment"), type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignment, FieldsSupersedesAlignment, FieldsAlignment>> getRelationshipSupersedesAlignment(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignment, FieldsSupersedesAlignment, FieldsAlignment>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignment, FieldsSupersedesAlignment, FieldsAlignment>>>>() {};
        List<List<Tuple3<FieldsAlignment, FieldsSupersedesAlignment, FieldsAlignment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SupersedesAlignment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsSupersededByAlignment</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment"), type {@link us.kbase.cdmientityapi.FieldsSupersedesAlignment FieldsSupersedesAlignment} (original type "fields_SupersedesAlignment"), type {@link us.kbase.cdmientityapi.FieldsAlignment FieldsAlignment} (original type "fields_Alignment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsAlignment, FieldsSupersedesAlignment, FieldsAlignment>> getRelationshipIsSupersededByAlignment(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsAlignment, FieldsSupersedesAlignment, FieldsAlignment>>>> retType = new TypeReference<List<List<Tuple3<FieldsAlignment, FieldsSupersedesAlignment, FieldsAlignment>>>>() {};
        List<List<Tuple3<FieldsAlignment, FieldsSupersedesAlignment, FieldsAlignment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsSupersededByAlignment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_SupersedesTree</p>
     * <pre>
     * This relationship connects a tree to the trees
     * it replaces.
     * It has the following fields:
     * =over 4
     * =item successor_type
     * Indicates whether sequences were removed or added to create the new tree.
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree"), type {@link us.kbase.cdmientityapi.FieldsSupersedesTree FieldsSupersedesTree} (original type "fields_SupersedesTree"), type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTree, FieldsSupersedesTree, FieldsTree>> getRelationshipSupersedesTree(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTree, FieldsSupersedesTree, FieldsTree>>>> retType = new TypeReference<List<List<Tuple3<FieldsTree, FieldsSupersedesTree, FieldsTree>>>>() {};
        List<List<Tuple3<FieldsTree, FieldsSupersedesTree, FieldsTree>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_SupersedesTree", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsSupersededByTree</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree"), type {@link us.kbase.cdmientityapi.FieldsSupersedesTree FieldsSupersedesTree} (original type "fields_SupersedesTree"), type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTree, FieldsSupersedesTree, FieldsTree>> getRelationshipIsSupersededByTree(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTree, FieldsSupersedesTree, FieldsTree>>>> retType = new TypeReference<List<List<Tuple3<FieldsTree, FieldsSupersedesTree, FieldsTree>>>>() {};
        List<List<Tuple3<FieldsTree, FieldsSupersedesTree, FieldsTree>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsSupersededByTree", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Treed</p>
     * <pre>
     * This relationship connects a tree to the source database from
     * which it was generated.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source"), type {@link us.kbase.cdmientityapi.FieldsTreed FieldsTreed} (original type "fields_Treed"), type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSource, FieldsTreed, FieldsTree>> getRelationshipTreed(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSource, FieldsTreed, FieldsTree>>>> retType = new TypeReference<List<List<Tuple3<FieldsSource, FieldsTreed, FieldsTree>>>>() {};
        List<List<Tuple3<FieldsSource, FieldsTreed, FieldsTree>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Treed", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsTreeFrom</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsTree FieldsTree} (original type "fields_Tree"), type {@link us.kbase.cdmientityapi.FieldsTreed FieldsTreed} (original type "fields_Treed"), type {@link us.kbase.cdmientityapi.FieldsSource FieldsSource} (original type "fields_Source")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsTree, FieldsTreed, FieldsSource>> getRelationshipIsTreeFrom(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsTree, FieldsTreed, FieldsSource>>>> retType = new TypeReference<List<List<Tuple3<FieldsTree, FieldsTreed, FieldsSource>>>>() {};
        List<List<Tuple3<FieldsTree, FieldsTreed, FieldsSource>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsTreeFrom", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_UsedIn</p>
     * <pre>
     * The UsedIn relationship defines which media is used by an
     * Environment.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsMedia FieldsMedia} (original type "fields_Media"), type {@link us.kbase.cdmientityapi.FieldsUsedIn FieldsUsedIn} (original type "fields_UsedIn"), type {@link us.kbase.cdmientityapi.FieldsEnvironment FieldsEnvironment} (original type "fields_Environment")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsMedia, FieldsUsedIn, FieldsEnvironment>> getRelationshipUsedIn(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsMedia, FieldsUsedIn, FieldsEnvironment>>>> retType = new TypeReference<List<List<Tuple3<FieldsMedia, FieldsUsedIn, FieldsEnvironment>>>>() {};
        List<List<Tuple3<FieldsMedia, FieldsUsedIn, FieldsEnvironment>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_UsedIn", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_HasMedia</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsEnvironment FieldsEnvironment} (original type "fields_Environment"), type {@link us.kbase.cdmientityapi.FieldsUsedIn FieldsUsedIn} (original type "fields_UsedIn"), type {@link us.kbase.cdmientityapi.FieldsMedia FieldsMedia} (original type "fields_Media")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsEnvironment, FieldsUsedIn, FieldsMedia>> getRelationshipHasMedia(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsEnvironment, FieldsUsedIn, FieldsMedia>>>> retType = new TypeReference<List<List<Tuple3<FieldsEnvironment, FieldsUsedIn, FieldsMedia>>>>() {};
        List<List<Tuple3<FieldsEnvironment, FieldsUsedIn, FieldsMedia>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_HasMedia", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_Uses</p>
     * <pre>
     * This relationship connects a genome to the machines that form
     * its metabolic pathways. A genome can use many machines, but a
     * machine is used by exactly one genome.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsUses FieldsUses} (original type "fields_Uses"), type {@link us.kbase.cdmientityapi.FieldsSSRow FieldsSSRow} (original type "fields_SSRow")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsUses, FieldsSSRow>> getRelationshipUses(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsUses, FieldsSSRow>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsUses, FieldsSSRow>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsUses, FieldsSSRow>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_Uses", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_IsUsedBy</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsSSRow FieldsSSRow} (original type "fields_SSRow"), type {@link us.kbase.cdmientityapi.FieldsUses FieldsUses} (original type "fields_Uses"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsSSRow, FieldsUses, FieldsGenome>> getRelationshipIsUsedBy(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsSSRow, FieldsUses, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsSSRow, FieldsUses, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsSSRow, FieldsUses, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_IsUsedBy", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_UsesCodons</p>
     * <pre>
     * This relationship connects a genome to the various codon usage
     * records for it.
     * It has the following fields:
     * =over 4
     * =back
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome"), type {@link us.kbase.cdmientityapi.FieldsUsesCodons FieldsUsesCodons} (original type "fields_UsesCodons"), type {@link us.kbase.cdmientityapi.FieldsCodonUsage FieldsCodonUsage} (original type "fields_CodonUsage")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsGenome, FieldsUsesCodons, FieldsCodonUsage>> getRelationshipUsesCodons(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsGenome, FieldsUsesCodons, FieldsCodonUsage>>>> retType = new TypeReference<List<List<Tuple3<FieldsGenome, FieldsUsesCodons, FieldsCodonUsage>>>>() {};
        List<List<Tuple3<FieldsGenome, FieldsUsesCodons, FieldsCodonUsage>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_UsesCodons", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_relationship_AreCodonsFor</p>
     * <pre>
     * </pre>
     * @param   ids   instance of list of String
     * @param   fromFields   instance of list of String
     * @param   relFields   instance of list of String
     * @param   toFields   instance of list of String
     * @return   instance of list of tuple of size 3: type {@link us.kbase.cdmientityapi.FieldsCodonUsage FieldsCodonUsage} (original type "fields_CodonUsage"), type {@link us.kbase.cdmientityapi.FieldsUsesCodons FieldsUsesCodons} (original type "fields_UsesCodons"), type {@link us.kbase.cdmientityapi.FieldsGenome FieldsGenome} (original type "fields_Genome")
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<Tuple3<FieldsCodonUsage, FieldsUsesCodons, FieldsGenome>> getRelationshipAreCodonsFor(List<String> ids, List<String> fromFields, List<String> relFields, List<String> toFields) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(ids);
        args.add(fromFields);
        args.add(relFields);
        args.add(toFields);
        TypeReference<List<List<Tuple3<FieldsCodonUsage, FieldsUsesCodons, FieldsGenome>>>> retType = new TypeReference<List<List<Tuple3<FieldsCodonUsage, FieldsUsesCodons, FieldsGenome>>>>() {};
        List<List<Tuple3<FieldsCodonUsage, FieldsUsesCodons, FieldsGenome>>> res = caller.jsonrpcCall("CDMI_EntityAPI.get_relationship_AreCodonsFor", args, retType, true, false);
        return res.get(0);
    }
}
