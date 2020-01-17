package com.evgen.controller;

import com.evgen.dto.DirFileDTO;
import com.evgen.dto.DirectoryDTO;
import com.evgen.dto.DirectoryExtDTO;
import com.evgen.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class MainController {

    @Autowired
    private DirectoryService directoryService;


    @GetMapping("/")
    public String getIndex(Model model) {

        return "redirect:/directories";
    }

    @GetMapping("/directories")
    public String getDirectories(Model model) {

        model.addAttribute("directories", directoryService.getAllDirectoriesExt());

        return "/directories";
    }

    @PostMapping("/directories")
    public String directories(@RequestParam(name = "directoryName") String directoryName,
                              Model model) {

        String directoryNameError = "";
        directoryName = directoryName.trim();
        boolean error = false;
        try {
            // VALIDATE
            File dir = new File(directoryName);
            boolean isDirectory = dir.isDirectory();
            if (isDirectory == false) {
                directoryNameError = "There is no such directory in the system!";
                error = true;
                return "/directories";
            }
            try {
                directoryName = dir.getCanonicalPath();
            } catch (IOException e) {
                directoryNameError = "There is no such directory in the system!";
                error = true;
                return "/directories";
            }

            // CHECKING FOR DUPLICATE
            DirectoryDTO testDirectoryDTO = directoryService.getByPath(directoryName);
            if (testDirectoryDTO != null) {
                directoryNameError = "This directory is already in use!";
                error = true;
                return "/directories";
            }

            // ADD DIRECTORY
            DirectoryDTO directoryDTO = new DirectoryDTO();
            directoryDTO.setPath(directoryName);
            directoryDTO.setAdded(LocalDateTime.now());
            directoryService.addDirectory(directoryDTO);
            model.addAttribute("directoryNameSuccess", "Directory was added!");


        } finally {

            model.addAttribute("directories", directoryService.getAllDirectoriesExt());

            if (error == true)
                model.addAttribute("directoryNameError", directoryNameError);
        }

        return "/directories";
    }

    @GetMapping("/delete")
    public String deleteDirectories(Model model) {

        int deleted = directoryService.deleteDirectories();
        model.addAttribute("directoryNameSuccess", "Deleted directories: " + deleted);
        model.addAttribute("directories", directoryService.getAllDirectoriesExt());

        return "/directories";
    }

    @GetMapping("/files")
    public String getFiles(@RequestParam(name = "id", required = false) String strDirectoryId,
                           Model model) {

        // VALIDATING
        int directoryId = 0;
        try {
            directoryId = Integer.parseInt(strDirectoryId);
        } catch (Exception e) {
            model.addAttribute("directory", null);
            return "/files";
        }

        // ALL RIGHT - FETCH DIRECTORY DETAILS
        List<DirFileDTO> dirFiles = directoryService.getFiles(directoryId);
        DirectoryDTO directory = directoryService.getDirectory(directoryId);

        model.addAttribute("directory", directory);
        model.addAttribute("files", dirFiles);

        return "/files";
    }

}
