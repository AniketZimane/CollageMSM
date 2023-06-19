package com.CollageMSM.CollageMSM.Controller;

import com.CollageMSM.CollageMSM.Dao.*;
import com.CollageMSM.CollageMSM.Entity.*;
import com.CollageMSM.CollageMSM.Utils.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Controller
public class StudentController
{
    @Autowired
    RegistrationRepo registrationRepo;
    @Autowired
    FileUploader uploader;
    @Autowired
    AdmissionRepo admissionRepo;
    @Autowired
    BonafideRequestRepo bonafideRequestRepo;
    @Autowired
    LivingRequestRepo livingRequestRepo;
    @Autowired
    ResultRepo resultRepo;

    @GetMapping("/")
    public String getHomePage()
    {
        return "index";
    }

    @GetMapping("/student/login/")
    public String loginPage()
    {
        return "loginform";
    }

    @GetMapping("/student/registration/")
    public String registrationPage()
    {
        return "registrationform";
    }

    @GetMapping("/student/amissionform/")
    public String admissionForm()
    {
        return "admissionForm";
    }

    @GetMapping("/student/bonafiderequest/")
    public String bonafideRequestForm()
    {
        return "bonafiderequestform";
    }

    @GetMapping("/student/livingrequest/")
    public String livingRequestForm()
    {
        return "liviingcirtiicaterequestform";
    }

    @GetMapping("/student/resultcheck/")
    public String checkResult()
    {
        return "reultpage";
    }


    @PostMapping("/student/savedata/")
    public String saveRegistrationData(Model model, Registration reg, MultipartFile file)
    {
        String redirectPage="registrationform";
        List<Registration> existingStudenteamil = registrationRepo.findByEmail(reg.getEmail().toString());
        List<Registration> existingStudentmobilenumber = registrationRepo.findByEmail(reg.getMobilenumber().toString());

        System.out.println("Existing eamil record " + existingStudenteamil);
        System.out.println("Existing mobile record " + existingStudentmobilenumber);
        if(existingStudenteamil.isEmpty() && existingStudentmobilenumber.isEmpty() )
        {
            String fileNameOld = file.getOriginalFilename();
            String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
            reg.setExtension(extension);

            Registration stuNew =registrationRepo.save(reg);
            String fileNameNew = stuNew.getId()+ "." + extension;

            uploader.uploadFile(file, fileNameNew);
            reg.setExtension(extension);
            model.addAttribute("msg","Registration successfully Completed");
            redirectPage="loginform";
        }
        else {
            model.addAttribute("msg","Student is already exist");
            redirectPage="registrationform";
        }
        return redirectPage;
    }
    @PostMapping("/student/amission/savedata/")
    public String saveAdmissionRecord(Model model, Admission addStu,MultipartFile file)
    {
        String redirectPage="admissionForm";
        List<Admission> existingStudenteamil = admissionRepo.findByEmailid(addStu.getEmailid().toString());
        List<Admission> existingStudentmobilenumber = admissionRepo.findByMobileno(addStu.getMobileno().toString());

        if(existingStudenteamil.isEmpty() && existingStudentmobilenumber.isEmpty() ) {
            String fileNameOld = file.getOriginalFilename();
            String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
            addStu.setExtension(extension);

            Admission admit = admissionRepo.save(addStu);
            String fileNameNew = admit.getId() + "." + extension;

            uploader.uploadFile(file, fileNameNew);
            addStu.setExtension(extension);
            model.addAttribute("msg", "Registration successfully Completed");
            redirectPage="index";
        }
        else {
            model.addAttribute("msg","Student is already exist");

        }
        return redirectPage;

    }
    @PostMapping("/student/bonafidrequest/savedata/")
    public String bonafideRequest(Model model, BonafideRequest br,MultipartFile file)
    {
        String redirectPage="index";
        String fileNameOld=file.getOriginalFilename();
        String extension=fileNameOld.substring(fileNameOld.indexOf(".")+1);
        br.setExtension(extension);
        BonafideRequest stuReqSave=bonafideRequestRepo.save(br);
        String fileNameNew=stuReqSave.getId()+"."+extension;
        uploader.uploadFile(file,fileNameNew);
        br.setExtension(extension);
        return redirectPage;
    }

    @PostMapping("/student/livingrequest/savedata/")
    public String livingRequest(Model model, LivingRequest lr, MultipartFile file)
    {
        String redirectPage="index";
        String fileNameOld=file.getOriginalFilename();
        String extension=fileNameOld.substring(fileNameOld.indexOf(".")+1);
        lr.setExtension(extension);
        LivingRequest stuReqSave=livingRequestRepo.save(lr);
        String fileNameNew=stuReqSave.getId()+"."+extension;
        uploader.uploadFile(file,fileNameNew);
        lr.setExtension(extension);
        return redirectPage;
    }
    @PostMapping("/student/result/")
    public String displayResult(Model model,String studId,String motherName)
    {
        int obtainMarks=0;
        int total=600;
        int percentage=0;
        String status="";
        List<Result> result=resultRepo.findByStudIdAndMotherName(studId,motherName);
        for(Result result1:result)
        {
            String marathm=result1.getMarathi();
            String hindim=result1.getHindi();
            String englishm=result1.getEnglish();
            String mathm=result1.getMath();
            String sciencem=result1.getScience();
            String socialscim=result1.getSocialsci();
            obtainMarks=Integer.parseInt(marathm)+Integer.parseInt(hindim)+Integer.parseInt(englishm)+Integer.parseInt(mathm)+Integer.parseInt(sciencem)+Integer.parseInt(socialscim);
            percentage=100*obtainMarks/600;
            System.out.println("total marks:"+percentage);
        }
        if(percentage>=35)
        {
            status="Pass";
        }
        else{
            status="Fail";
        }
        Admission data=admissionRepo.getReferenceById(Integer.parseInt(studId));
        System.out.println("Student data:"+data);
        System.out.println(result);
        model.addAttribute("stuId",studId);
        model.addAttribute("motherName",motherName);
        model.addAttribute("data",data);
        model.addAttribute("result",result);
        model.addAttribute("total",obtainMarks);
        model.addAttribute("percentage",percentage);
        model.addAttribute("status",status);
        return "displayResult";
    }


}
