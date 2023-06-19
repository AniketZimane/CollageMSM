package com.CollageMSM.CollageMSM.Controller;

import com.CollageMSM.CollageMSM.Dao.*;
import com.CollageMSM.CollageMSM.Entity.*;
import com.CollageMSM.CollageMSM.Utils.DateHelper;
import com.CollageMSM.CollageMSM.Utils.FileUploader;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller
public class StudentController {
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
    @Autowired
    ImportantDateRepo importantDateRepo;
    @Autowired
    StudentsAditionalDetailsRepo studentsAditionalDetailsRepo;
    @Autowired
    HttpSession session;

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/student/login/")
    public String loginPage() {
        return "loginform";
    }

    @GetMapping("/student/registration/")
    public String registrationPage() {
        return "registrationform";
    }

    @GetMapping("/student/amissionform/")
    public String admissionForm() {
        return "admissionForm";
    }

    @GetMapping("/student/bonafiderequest/")
    public String bonafideRequestForm() {
        return "bonafiderequestform";
    }

    @GetMapping("/student/livingrequest/")
    public String livingRequestForm() {
        return "liviingcirtiicaterequestform";
    }

    @GetMapping("/student/resultcheck/")
    public String checkResult() {
        return "reultpage";
    }

    @GetMapping("/student/requesttrack/")
    public String requestTrackingPage(Model model) {
        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder captchaCode = new StringBuilder();

        // Generate random characters for the captcha code
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(charset.length());
            captchaCode.append(charset.charAt(index));
        }
        model.addAttribute("code", captchaCode);
        return "requesttracking";
    }

    @GetMapping("/student/importantdate/")
    public String importantDate(Model model) {
        List<ImportantNotification> notificationList = importantDateRepo.findAll();
        model.addAttribute("notificationList", notificationList);
        return "studentImportantDateRecord";
    }

    @PostMapping("/student/savedata/")
    public String saveRegistrationData(Model model, Registration reg, MultipartFile file) {

        String redirectPage = "registrationform";
        List<Registration> existingStudenteamil = registrationRepo.findByEmail(reg.getEmail().toString());
        List<Registration> existingStudentmobilenumber = registrationRepo.findByEmail(reg.getMobilenumber().toString());

        System.out.println("Existing eamil record " + existingStudenteamil);
        System.out.println("Existing mobile record " + existingStudentmobilenumber);
        if (existingStudenteamil.isEmpty() && existingStudentmobilenumber.isEmpty()) {
            String fileNameOld = file.getOriginalFilename();
            String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
            reg.setExtension(extension);

            Registration stuNew = registrationRepo.save(reg);
            String fileNameNew = stuNew.getId() + "." + extension;

            uploader.uploadFile(file, fileNameNew);
            reg.setExtension(extension);
            model.addAttribute("msg", "Registration successfully Completed");
            redirectPage = "loginform";
        } else {
            model.addAttribute("msg", "Student is already exist");
            redirectPage = "registrationform";
        }
        return redirectPage;
    }

    @PostMapping("/student/amission/savedata/")
    public String saveAdmissionRecord(Model model, Admission addStu, MultipartFile file) {

        String redirectPage = "admissionForm";
        List<Admission> existingStudenteamil = admissionRepo.findByEmailid(addStu.getEmailid().toString());
        List<Admission> existingStudentmobilenumber = admissionRepo.findByMobileno(addStu.getMobileno().toString());

        if (existingStudenteamil.isEmpty() && existingStudentmobilenumber.isEmpty()) {
            String fileNameOld = file.getOriginalFilename();
            String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
            addStu.setExtension(extension);

            Admission admit = admissionRepo.save(addStu);
            String fileNameNew = admit.getId() + "." + extension;

            uploader.uploadFile(file, fileNameNew);
            addStu.setExtension(extension);
            model.addAttribute("msg", "Registration successfully Completed");
            redirectPage = "index";
        } else {
            model.addAttribute("emsg", "Student is already exist");

        }
        return redirectPage;

    }

    @PostMapping("/student/bonafidrequest/savedata/")
    public String bonafideRequest(Model model, BonafideRequest br, MultipartFile file) {
        String redirectPage = "index";
        String fileNameOld = file.getOriginalFilename();
        String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
        br.setExtension(extension);
        BonafideRequest stuReqSave = bonafideRequestRepo.save(br);
        String fileNameNew = stuReqSave.getId() + "." + extension;
        uploader.uploadFile(file, fileNameNew);
        br.setExtension(extension);
        System.out.println("Your tracking id is:" + stuReqSave.getId());
        return redirectPage;
    }

    @PostMapping("/student/livingrequest/savedata/")
    public String livingRequest(Model model, LivingRequest lr, MultipartFile file) {
        String redirectPage = "index";
        List<LivingRequest> exstingRequest = livingRequestRepo.findByStudentId(lr.getStudentId().toString());
        if (exstingRequest.isEmpty()) {
            System.out.println("existing request" + exstingRequest);
            String fileNameOld = file.getOriginalFilename();
            String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
            lr.setExtension(extension);
            LivingRequest stuReqSave = livingRequestRepo.save(lr);
            String fileNameNew = stuReqSave.getId() + "." + extension;
            uploader.uploadFile(file, fileNameNew);
            lr.setExtension(extension);
            System.out.println("Your tracking id is:" + stuReqSave.getId());

        } else {
            model.addAttribute("msg", "Sorry you have already requested for leaving certificate or already taken");
            redirectPage = "liviingcirtiicaterequestform";
        }
        return redirectPage;
    }

    @PostMapping("/student/result/")
    public String displayResult(Model model, String studId, String motherName) {
        int obtainMarks = 0;
        int total = 600;
        int percentage = 0;
        String status = "";
        List<Result> result = resultRepo.findByStudIdAndMotherName(studId, motherName);
        for (Result result1 : result) {
            String marathm = result1.getMarathi();
            String hindim = result1.getHindi();
            String englishm = result1.getEnglish();
            String mathm = result1.getMath();
            String sciencem = result1.getScience();
            String socialscim = result1.getSocialsci();
            obtainMarks = Integer.parseInt(marathm) + Integer.parseInt(hindim) + Integer.parseInt(englishm)
                    + Integer.parseInt(mathm) + Integer.parseInt(sciencem) + Integer.parseInt(socialscim);
            percentage = 100 * obtainMarks / 600;
            System.out.println("total marks:" + percentage);
        }
        if (percentage >= 35) {
            status = "Pass";
        } else {
            status = "Fail";
        }
        Admission data = admissionRepo.getReferenceById(Integer.parseInt(studId));
        System.out.println("Student data:" + data);
        System.out.println(result);
        model.addAttribute("stuId", studId);
        model.addAttribute("motherName", motherName);
        model.addAttribute("data", data);
        model.addAttribute("result", result);
        model.addAttribute("total", obtainMarks);
        model.addAttribute("percentage", percentage);
        model.addAttribute("status", status);
        return "displayResult";
    }

    @PostMapping("/student/request/tracking/")
    public String requestTracking(Model model, Integer trackId) {
        String redirectpage = "requesttracking";
        BonafideRequest checkingbonafiderequest = bonafideRequestRepo.getReferenceById(trackId);
        LivingRequest checkinglivingrequest = livingRequestRepo.getReferenceById(trackId);
        StudentsAditionalDetails studentsAditionalDetails = studentsAditionalDetailsRepo
                .findByStudId(checkinglivingrequest.getStudentId().toString());
        System.out.println("Adtional details:" + studentsAditionalDetails);
        if (checkingbonafiderequest != null) {
            System.out.println("Inside bonafide block");
            Integer id = 0;
            String status = "1";
            List<BonafideRequest> bonafideRequestList = bonafideRequestRepo.findByIdAndStatus(trackId, status);
            if (!bonafideRequestList.isEmpty()) {
                for (BonafideRequest data : bonafideRequestList) {
                    id = Integer.parseInt(data.getStudentId().toString());
                }
                Admission admission = admissionRepo.getReferenceById(id);
                model.addAttribute("data", admission);
                redirectpage = "bonafiedceritificate";
            } else {
                String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                StringBuilder captchaCode = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < 6; i++) {
                    int index = random.nextInt(charset.length());
                    captchaCode.append(charset.charAt(index));
                }
                model.addAttribute("code", captchaCode);

                if (checkinglivingrequest != null && studentsAditionalDetails != null) {
                    System.out.println("Inside living block");
                    List<LivingRequest> livingRequestList = livingRequestRepo.findByIdAndStatus(trackId, status);
                    if (!livingRequestList.isEmpty()) {
                        for (LivingRequest data : livingRequestList) {
                            id = Integer.parseInt(data.getStudentId().toString());
                        }
                        Admission admission = admissionRepo.getReferenceById(id);
                        model.addAttribute("data", admission);
                        model.addAttribute("date", admission.getDob());
                        model.addAttribute("additionalData", studentsAditionalDetails);
                        model.addAttribute("dates", new DateHelper());
                        redirectpage = "leavingcertificate";
                    } else {
                        charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                        captchaCode = new StringBuilder();

                        // Generate random characters for the captcha code
                        random = new Random();
                        for (int i = 0; i < 6; i++) {
                            int index = random.nextInt(charset.length());
                            captchaCode.append(charset.charAt(index));
                        }
                        model.addAttribute("code", captchaCode);
                        model.addAttribute("infoMsg", "Your request is currently pending or Invalid tracking id ");
                    }

                }
            }
        } else {
            model.addAttribute("errorMsg", "Invalid tracking id");
        }

        return redirectpage;
    }
}
