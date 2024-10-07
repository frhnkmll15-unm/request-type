package co.id.mcs.eFinProc.request_type.Web.Controller;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 20/09/2024 14:12
@Last Modified 20/09/2024 14:12
Version 1.0
*/



import co.id.mcs.eFinProc.FilterSearch;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.DeleteStatementAlgorithmById;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.ParamSearchStatementAlgorithm;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.UpdateStatementAlgorithmById;
import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;
import co.id.mcs.eFinProc.request_type.service.StatementAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/statement-algorithm")
public class StatementAlgorithmController {

    @Autowired
    StatementAlgorithmService statementAlgorithmService;

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<?>> getStatementAlgorithms(
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) List<String> fields,
            @RequestParam int page,
            @RequestParam int limit,
            @RequestParam(required = false) Integer status) {
        Search search = new Search();
        search.setValue(searchValue);

        if (fields != null && !fields.isEmpty()) {
            Set<FilterSearch> filters = new HashSet<>();
            for (String field : fields) {
                FilterSearch filter = new FilterSearch();
                filter.setField(field);
                filter.setAliasField(null); // Atur aliasField sesuai kebutuhan
                filters.add(filter);
            }
            search.setFilter(filters);
        }
        ParamSearchStatementAlgorithm param = new ParamSearchStatementAlgorithm();
        param.setPage(page);
        param.setLimit(limit);
        if (status != null) {
            param.setStatus(status);
        }
        BaseResponse<?> response = statementAlgorithmService.getStatementAlgorithms(search, param);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<StatementAlgorithmDto>> createStatementAlgorithm(@RequestBody StatementAlgorithmDto statementAlgorithm, String statementAlgorithmServicee) {
        System.out.println("Received requestType: " + statementAlgorithmServicee);
        BaseResponse<StatementAlgorithmDto> response = statementAlgorithmService.createStatementAlgorithm(statementAlgorithm);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<BaseResponse<StatementAlgorithmDto>> getStatementAlgorithmById(@PathVariable int id) throws Exception {
        System.out.println("Fetching request type with ID: " + id);
        BaseResponse<StatementAlgorithmDto> response = statementAlgorithmService.getStatementAlgorithmById(1, id);
        if ("404".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<UpdateStatementAlgorithmById>> updateStatementAlgorithmById(@PathVariable int id, @RequestBody UpdateStatementAlgorithmById updateStatementAlgorithmById) throws Exception{
        updateStatementAlgorithmById.setId(id);
        BaseResponse<UpdateStatementAlgorithmById> response = statementAlgorithmService.updateStatementAlgorithmById(updateStatementAlgorithmById);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<DeleteStatementAlgorithmById>> deleteStatementAlgorithmById(
            @PathVariable int id) throws Exception {
        DeleteStatementAlgorithmById deleteStatement = new DeleteStatementAlgorithmById();
        deleteStatement.setId(id);
        BaseResponse<DeleteStatementAlgorithmById> response = statementAlgorithmService.deleteStatementAlgorithmById(deleteStatement);
        if ("404".equals(response.getStatus())) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

}
