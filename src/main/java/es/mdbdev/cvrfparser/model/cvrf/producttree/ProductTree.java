package es.mdbdev.cvrfparser.model.cvrf.producttree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 28/01/2017.
 */
public class ProductTree
{
    private List<Branch> branches;
    private List<FullProductName> fullProductNames;

    public void addBranch(Branch b){
        branches.add(b);
    }
    public void addFullProductName(FullProductName n){
        fullProductNames.add(n);
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<FullProductName> getFullProductNames() {
        return fullProductNames;
    }

    public void setFullProductNames(List<FullProductName> fullProductNames) {
        this.fullProductNames = fullProductNames;
    }

    public ProductTree(){
        branches = new ArrayList<Branch>();
        fullProductNames = new ArrayList<FullProductName>();
    }


    @Override
    public String toString()
    {
        return "ClassPojo [branches = "+branches+", FullProductNames = "+fullProductNames+"]";
    }
}
