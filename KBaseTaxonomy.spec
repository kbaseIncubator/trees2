module KBaseTaxonomy
{
    /* A workspace ID that references a Genome data object.
        @id ws KBaseGenomes.Genome
    */
    typedef string genome_ref;

    /* A workspace ID that references a Taxon data object.
        @id ws KBaseTaxonomy.Taxon
    */
    typedef string taxon_ref;

    /* Data type for taxonomy node.
        @optional parent_id parent_ref rank mito_genetic_code division
    */
    typedef structure {
        int tax_id;
        int parent_id;
        taxon_ref parent_ref;
        string name;
        list<string> aliases;
        string rank;
        int genetic_code;
        int mito_genetic_code;
        string division;
    } Taxon;

    typedef structure {
        genome_ref genome_ref;
        taxon_ref taxon_ref;
    } GenomeTaxon;
};
