package hotel.model.service;

import hotel.model.entity.Branch;
import hotel.model.repository.BranchRepository;
import lombok.Getter;

import java.util.List;

public final class BranchService {
    @Getter
    private static BranchService service = new BranchService();

    private BranchService() {}

    public void save(Branch branch) throws Exception {
        try(BranchRepository branchRepository = new BranchRepository()) {
            branchRepository.save(branch);
        }
    }

    public void update(Branch branch) throws Exception {
        try(BranchRepository branchRepository = new BranchRepository()) {
            branchRepository.edit(branch);
        }
    }

    public void delete(Integer id) throws Exception {
        try(BranchRepository branchRepository = new BranchRepository()) {
            branchRepository.delete(id);
        }
    }

    public void edit(Branch branch) throws Exception {
        try(BranchRepository branchRepository = new BranchRepository()) {
            branchRepository.edit(branch);
        }
    }

    public Branch findById(Integer id) throws Exception {
        try(BranchRepository branchRepository = new BranchRepository()) {
            return branchRepository.findById(id);
        }
    }

    public List<Branch> findAll() throws Exception {
        try(BranchRepository branchRepository = new BranchRepository()) {
            return branchRepository.findAll();
        }
    }

    // Branch-specific methods
    public List<Branch> findByAddress(String address) throws Exception {
        try(BranchRepository branchRepository = new BranchRepository()) {
            return branchRepository.findByAddress(address);
        }
    }
}
