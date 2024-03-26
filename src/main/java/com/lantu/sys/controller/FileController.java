package com.lantu.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lantu.common.utils.HadoopUtil;
import com.lantu.common.vo.Result;
import com.lantu.sys.entity.File;
import com.lantu.sys.entity.User;
import com.lantu.sys.service.IFileService;
import com.lantu.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuange
 * @since 2023-09-05
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private IFileService fileService;

    @Autowired
    private IUserService userService;

    @Autowired
    private HadoopUtil hadoopUtil;
    @GetMapping("/list")
    public Result<Map<String,Object>> getFileList(@RequestParam(value = "fileName",required = false) String fileName,
                                                  @RequestParam(value = "pageNo") Long pageNo,
                                                  @RequestParam(value = "pageSize") Long pageSize,
                                                  @RequestParam(value = "type") String fileType,
                                                  @RequestHeader("X-Token") String token){
        User user= userService.getUserByToken(token);
        LambdaQueryWrapper<File> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasLength(fileName)) {
            wrapper.eq(File::getFileName, fileName);
        }
        System.out.println(user);
        wrapper.eq(File::getUserId, user.getId()).eq(File::getFileType, fileType);
        Page<File> page = new Page<>(pageNo,pageSize);
        fileService.page(page,wrapper);
        Map<String,Object>data=new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }
    @PostMapping("/uploadFile")
    public Result<Object> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("fileType") String fileType) {
        if (file.isEmpty()) {
            return Result.fail("Please select a file to upload!");
        }

        try {
            // 读取文件内容并写入Hadoop
            boolean success = hadoopUtil.writeFile(file.getOriginalFilename(), file.getInputStream(), fileType);
            if (success) {
                return Result.success("File uploaded successfully!");
            } else {
                return Result.fail("Failed to upload file!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/readFile")
    public Result<Object> readFileTest(@RequestParam String path) {
        try {
            String content = hadoopUtil.readFile(path);
            return Result.success(content);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("Failed to read file from HDFS: " + e.getMessage());
        }
    }
//    @PostMapping
//    public Result<?> addRole(@RequestBody Role role){
//
//
//
//        roleService.addRole(role);
//        return Result.success("新增成功");
//
//    }
//    @PutMapping
//    public Result<?> upDateRole(@RequestBody Role role){
//        roleService.upDateRole(role);
//        return Result.success("修改成功");
//
//    }
//
//
//    @GetMapping("/{id}")
//    public Result<Role> getRoleById(@PathVariable("id") Integer id){
//        Role role =roleService.getRoleById(id);
//        return  Result.success(role);
//    }
//    @DeleteMapping("/{id}")
//    public Result<Role> deleteRoleById(@PathVariable("id") Integer id){
//        roleService.deleteRoleById(id);
//        return  Result.success("删除成功");
//    }
//    @GetMapping("/all")
//    public Result<List<Role>> getAllRole(){
//        List<Role> roleList=roleService.list();
//        return Result.success(roleList);
//    }
}
