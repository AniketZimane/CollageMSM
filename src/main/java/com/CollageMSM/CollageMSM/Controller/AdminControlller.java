package com.CollageMSM.CollageMSM.Controller;

import com.CollageMSM.CollageMSM.Dao.*;
import com.CollageMSM.CollageMSM.Entity.*;
import com.CollageMSM.CollageMSM.Service.EmailService;
import com.CollageMSM.CollageMSM.Utils.DateHelper;
import com.CollageMSM.CollageMSM.Utils.FileUploader;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.expression.Strings;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AdminControlller {
    @Autowired
    AdmissionRepo admissionRepo;
    @Autowired
    LivingRequestRepo livingRequestRepo;
    @Autowired
    BonafideRequestRepo bonafideRequestRepo;
    @Autowired
    ResultRepo resultRepo;
    @Autowired
    ImportantDateRepo importantDateRepo;
    @Autowired
    RegistrationRepo registrationRepo;
    @Autowired
    StudentsAditionalDetailsRepo studentsAditionalDetailsRepo;
    @Autowired
    CutoffRepo cutoffRepo;
    @Autowired
    SeatReservationRepo seatReservationRepo;
    @Autowired
    FeesCriteriaRepo feesCriteriaRepo;
    @Autowired
    HttpSession session;
    @Autowired
    FileUploader uploader;
    @Autowired
    JavaMailSender mailSender;

    int maxSize = 5;

    @GetMapping("/admin/dashboard/")
    public String adminDashboard(Model model) {
        String status = "1";
        List<LivingRequest> livingRequestList = livingRequestRepo.findAll();
        List<Admission> admissionformfillList = admissionRepo.findAll();
        List<BonafideRequest> bonafideRequestList = bonafideRequestRepo.findAll();
        List<Admission> admittedStudent = admissionRepo.findByStatus(status);
        List<Result> resultList = resultRepo.findAll();
        Integer stucount = admissionformfillList.size();
        Integer livingRequestCount = livingRequestList.size();
        Integer bonafideRequestCount = bonafideRequestList.size();
        Integer admittedStudentCount = admittedStudent.size();
        Integer resultCount = resultList.size();
        model.addAttribute("count", stucount);
        model.addAttribute("livingRequestCount", livingRequestCount);
        model.addAttribute("bonafideRequestCount", bonafideRequestCount);
        model.addAttribute("admittedStudentCount", admittedStudentCount);
        model.addAttribute("resultCount", resultCount);
        return "admidsashboard";
    }

    @GetMapping("/login")
    public String adminLogin() {
        return "adminogin";
    }

    @GetMapping("/admin/register/")
    public String adminRegister() {
        return "adminregister";
    }

    @GetMapping("/admin/castwise/distribution/")
    public String castWiseDistributionPage() {
        return "AdmincastWiseDistribution";
    }

    @GetMapping("/admin/cutoff/")
    public String cutOffPage() {
        return "AdminCutOff";
    }

    @GetMapping("/admin/fees/criteria/")
    public String feesCriteriaPage() {
        return "AdminFeesCriteria";
    }

    @GetMapping("/admin/forgotpasswordpage/")
    public String porgotPasswordPage() {
        return "AdminForgetPassword";
    }

    @GetMapping("/admin/meritlist/")
    public String meritListPage(Model model) {
        int curPage = 1;
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<Admission> page = admissionRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<Admission> admissionList = page.toList();
        List<Admission> meritListStudent = admissionRepo.findByPercentageGreaterThan80AndStatus();
        model.addAttribute("admissionList", admissionList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("stulrList", meritListStudent);
        return "AdminMeritList";
    }

    @GetMapping("/admin/meritlist/{curPage}/")
    public String meritListPageCurPage(Model model, @PathVariable int curPage) {
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<Admission> page = admissionRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<Admission> admissionList = page.toList();
        List<Admission> meritListStudent = admissionRepo.findByPercentageGreaterThan80AndStatus();
        model.addAttribute("admissionList", admissionList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("stulrList", meritListStudent);
        return "AdminMeritList";
    }

    @GetMapping("/admin/leavingrequest/record/")
    public String leavingrequest(Model model) {
        int curPage = 1;
        Pageable pageable = PageRequest.of(curPage, maxSize, Sort.by("id").descending());
        Page<LivingRequest> page = livingRequestRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<LivingRequest> livingRequestList = page.toList();
        List<LivingRequest> livingRequestList1 = livingRequestRepo.findAll();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("stulrList", livingRequestList);
        model.addAttribute("stulrList", livingRequestList1);
        model.addAttribute("recordSize", livingRequestList1.size());
        return "requestedLeavingRecord";
    }

    @GetMapping("/admin/leavingrequest/record/{curPage}/")
    public String leavingrequestCurPage(Model model, @PathVariable int curPage) {
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<LivingRequest> page = livingRequestRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<LivingRequest> livingRequestList1 = livingRequestRepo.findAll();
        List<LivingRequest> livingRequestList = page.toList();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("stulrList", livingRequestList);
        model.addAttribute("recordSize", livingRequestList1.size());

        return "requestedLeavingRecord";
    }

    @GetMapping("/admin/important/record/")
    public String importantRecord(Model model) {
        List<CutOff> cutOffList = cutoffRepo.findAll();
        List<FeesCriteria> feesCriteria = feesCriteriaRepo.findAll();
        List<SeatReservation> seatReservations = seatReservationRepo.findAll();
        model.addAttribute("cutOffList", cutOffList);
        model.addAttribute("feesCriteria", feesCriteria);
        model.addAttribute("seatReservations", seatReservations);
        return "Report";
    }

    @GetMapping("/admin/bonafideRequest/record/")
    public String bonafideRequest(Model model) {
        int curPage = 1;
        Pageable pageable = PageRequest.of(curPage, maxSize, Sort.by("id").descending());
        Page<BonafideRequest> page = bonafideRequestRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<BonafideRequest> bonafideRequestList = page.toList();
        List<BonafideRequest> bonafideRequestList1 = bonafideRequestRepo.findAll();
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("stulrList", bonafideRequestList);
        model.addAttribute("stulrList", bonafideRequestList1);
        model.addAttribute("recordSize", bonafideRequestList1.size());
        return "requestedBonafideRecord";
    }

    @GetMapping("/admin/bonafideRequest/record/{curPage}/")
    public String bonafideRequestCurPage(Model model, @PathVariable int curPage) {
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<BonafideRequest> page = bonafideRequestRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<BonafideRequest> bonafideRequestList = page.toList();
        List<BonafideRequest> bonafideRequestList1 = bonafideRequestRepo.findAll();
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("stulrList", bonafideRequestList);
        model.addAttribute("stulrList", bonafideRequestList1);
        model.addAttribute("recordSize", bonafideRequestList1.size());
        return "requestedBonafideRecord";
    }

    @GetMapping("/admin/leavingcertificate/{id}/")
    public String displayLeavingCertificate(Model model, @PathVariable int id) {
        LivingRequest livingRequest = livingRequestRepo.getReferenceById(id);
        Integer stuId = Integer.parseInt(livingRequest.getStudentId().toString());
        Admission admission = admissionRepo.getReferenceById(stuId);
        StudentsAditionalDetails studentsAditionalDetails = studentsAditionalDetailsRepo.findByStudId(stuId.toString());
        model.addAttribute("data", admission);
        model.addAttribute("date", admission.getDob());
        model.addAttribute("additionalData", studentsAditionalDetails);
        model.addAttribute("dates", new DateHelper());
        return "leavingcertificate";
    }

    @GetMapping("/admin/result/record/")
    public String getResultReport(Model model) {
        int curPage = 1;
        Pageable pageable = PageRequest.of(curPage, maxSize, Sort.by("id").descending());
        Page<Result> page = resultRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<Result> resultList = page.toList();
        List<Result> resultList1 = resultRepo.findAll();
        model.addAttribute("resultList", resultList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("resultList", resultList1);
        model.addAttribute("recordSize", resultList1.size());
        return "resultReport";
    }

    @GetMapping("/admin/resultreport/{curPage}/")
    public String getResultReportCurPage(Model model, @PathVariable int curPage) {
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<Result> page = resultRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<Result> resultList = page.toList();
        List<Result> resultList1 = resultRepo.findAll();
        model.addAttribute("resultList", resultList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("resultList", resultList1);
        model.addAttribute("recordSize", resultList1.size());
        return "resultReport";
    }

    @GetMapping("/admin/delete/resultrecord/{id}/")
    public String deleteResultRecord(Model model, @PathVariable int id) {
        resultRepo.deleteById(id);
        int curPage = 1;
        Pageable pageable = PageRequest.of(curPage, maxSize, Sort.by("id").descending());
        Page<Result> page = resultRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<Result> resultList = page.toList();
        List<Result> resultList1 = resultRepo.findAll();
        model.addAttribute("resultList", resultList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("resultList", resultList1);
        model.addAttribute("recordSize", resultList1.size());
        return "resultReport";
    }

    @GetMapping("/admin/update/resultrecord/{id}/")
    public String updateResultRecord(Model model, @PathVariable int id) {
        session.setAttribute("id", id);
        Result resultrecord = resultRepo.getReferenceById(id);
        model.addAttribute("data", resultrecord);
        return "adminuploadresult";
    }

    @GetMapping("/admin/admissinrecord/")
    public String admssionRecord(Model model) {
        int curPage = 1;
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<Admission> page = admissionRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<Admission> admissionList = page.toList();
        List<Admission> admissionList1 = admissionRepo.findAll();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("stuList", admissionList);
        model.addAttribute("admissionList1", admissionList1);
        model.addAttribute("recordSize", admissionList1.size());
        return "admissiontable";
    }

    @GetMapping("/admin/additional/details/")
    public String getAdditionalDetails(Model model) {
        int curPage = 1;
        Pageable pageable = PageRequest.of(curPage, maxSize, Sort.by("id").descending());
        Page<StudentsAditionalDetails> page = studentsAditionalDetailsRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<StudentsAditionalDetails> studentsAditionalDetailsList = page.toList();
        List<StudentsAditionalDetails> studentsAditionalDetailsList1 = studentsAditionalDetailsRepo.findAll();
        model.addAttribute("studentsAditionalDetails", studentsAditionalDetailsList);
        model.addAttribute("studentsAditionalDetails", studentsAditionalDetailsList1);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("recordSize", page.getSize());
        model.addAttribute("curPage", curPage);
        model.addAttribute("recordSize", studentsAditionalDetailsList1.size());
        return "AdtionalDetailsRecord";
    }

    @GetMapping("/admin/additional/details/{curPage}/")
    public String getAdditionalDetailsCurPage(Model model, @PathVariable int curPage) {
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<StudentsAditionalDetails> page = studentsAditionalDetailsRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<StudentsAditionalDetails> studentsAditionalDetailsList = page.toList();
        model.addAttribute("studentsAditionalDetails", studentsAditionalDetailsList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        return "AdtionalDetailsRecord";
    }

    @PostMapping("/admin/student/update/aditional/deails/")
    public String updateAdditionalDetails(Model model, String studId, String scheduledCastOpn, String currentClass, String examStu, String subject, String promotionStu, String fees, String feesConcession, String generalConduct, String reason) {
        if(session.getAttribute("id")!=null)
        {
            StudentsAditionalDetails studentsAditionalDetails = studentsAditionalDetailsRepo.findByStudId(studId);
            Integer id = studentsAditionalDetails.getId();
            studentsAditionalDetailsRepo.save(new StudentsAditionalDetails(id, studId, scheduledCastOpn, currentClass, examStu, subject, promotionStu, fees, feesConcession, generalConduct, reason));
            List<StudentsAditionalDetails> studentsAditionalDetailsList = studentsAditionalDetailsRepo.findAll();
            model.addAttribute("studentsAditionalDetails", studentsAditionalDetailsList);
            model.addAttribute("msg", "Record updated successfully!");
        }
        else{
            List<StudentsAditionalDetails> studentsAditionalDetailsList = studentsAditionalDetailsRepo.findAll();
            model.addAttribute("studentsAditionalDetails", studentsAditionalDetailsList);
            model.addAttribute("emsg", "Something went wrong!");

        }

        return "AdtionalDetailsRecord";
    }

    @GetMapping("/admin/update/admissionrecord/{id}/")
    public String updateAdmissionRecord(Model model, @PathVariable Integer id) {
        session.setAttribute("id", id);
        Admission admission = admissionRepo.getReferenceById(id);
        model.addAttribute("data", admission);
        return "admissionRecordUpdateForm";
    }
    @PostMapping("/admin/amission/recordupdate/")
    public String updateAdmissionRecord(Model model, Admission addstu, MultipartFile file, String stuclass,
                                        String fname, String mname, String lname, String motherName, String dob, String emailid,
                                        String lastschoolName, String mobileno, String country, String state, String district, String hobbies,
                                        String age, String percentage, String extension, String skills, String fqualification,
                                        String mqualification, String cast, String gender, String pnumber, String peraddress, String tempaddress,
                                        Integer currentYear) {

        int curPage=1;
        Integer id=Integer.parseInt(session.getAttribute("id").toString());
        Admission admission = admissionRepo.getReferenceById(id);
        LocalDate admissionDate=admission.getAdmissionDate();
        System.out.println("Admission date:"+admissionDate);
        String fileNameOld = file.getOriginalFilename();
        String fileexpension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
        addstu.setExtension(fileexpension);
        String fileNameNew = id + "." + extension;
        uploader.uploadFile(file, fileNameNew);
        addstu.setExtension(extension);
        String status = "1";
        System.out.println("Session id:"+id);
        admissionRepo.save(new Admission(id, stuclass, fname, mname, lname, motherName, dob, emailid, lastschoolName,
                mobileno, country, state, district, hobbies, age, percentage, extension, skills, fqualification,
                mqualification, cast, gender, pnumber, peraddress, tempaddress, status, admissionDate,
                currentYear));
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<Admission> page = admissionRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<Admission> admissionList = page.toList();
        List<Admission> admissionformfillList = admissionRepo.findAll();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("stuList", admissionList);
        model.addAttribute("recordSize", admissionformfillList.size());
        model.addAttribute("msg", "Record Updated successfully!");
        session.invalidate();
        return "admissiontable";
    }

    @GetMapping("/admin/delete/additionaldatda/{id}/")
    public String deleteAditionalData(Model model, @PathVariable Integer id) {
        int curPage=1;
        studentsAditionalDetailsRepo.deleteById(id);
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<StudentsAditionalDetails> page = studentsAditionalDetailsRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<StudentsAditionalDetails> studentsAditionalDetailsList = page.toList();
        model.addAttribute("studentsAditionalDetails", studentsAditionalDetailsList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        return "AdtionalDetailsRecord";
    }

    @GetMapping("/admin/admissinrecord/{curPage}/")
    public String admssionRecordCurrentPage(Model model, @PathVariable int curPage) {
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<Admission> page = admissionRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<Admission> admissionList = page.toList();
        List<Admission> admissionformfillList = admissionRepo.findAll();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("stuList", admissionList);
        model.addAttribute("recordSize", admissionformfillList.size());

        // List<Admission> admissionformfillList=admissionRepo.findAll();
        // model.addAttribute("stuList",admissionformfillList);
        return "admissiontable";
    }

    @GetMapping("/admin/cuoff/record/update/{id}/")
    public String cutoffRecordUpdate(@PathVariable Integer id) {
        session.setAttribute("cutoffId", id);
        return "AdminCutOff";
    }

    @GetMapping("/admin/seat/record/update/{id}/")
    public String seatRecordUpdate(@PathVariable Integer id) {
        session.setAttribute("seatId", id);
        return "AdmincastWiseDistribution";
    }

    @GetMapping("/admin/fees/record/update/{id}/")
    public String feesRecordUpdate(@PathVariable Integer id) {
        session.setAttribute("feesId", id);
        return "AdminFeesCriteria";
    }

    @GetMapping("/admin/leavingrequest/update/{id}/")
    public String leavingRequestUpdate(Model model, @PathVariable Integer id) {
        String status = "";
        LivingRequest livingRequestrecord = livingRequestRepo.getReferenceById(id);
        if (livingRequestrecord.getStatus() == null || livingRequestrecord.getStatus().equals("0")) {
            status = "1";
        } else {
            status = "0";
        }
        livingRequestRepo.save(new LivingRequest(id, livingRequestrecord.getStudentId(),
                livingRequestrecord.getFirstname(), livingRequestrecord.getLastname(), livingRequestrecord.getEmail(),
                livingRequestrecord.getStuclass(), livingRequestrecord.getExtension(), livingRequestrecord.getReason(),
                status));
        List<LivingRequest> updaterequestrecord = livingRequestRepo.findAll();
        model.addAttribute("stulrList", updaterequestrecord);
        return "requestedLeavingRecord";
    }

    @GetMapping("/admin/bonafiderequest/update/{id}/")
    public String bonafideRequestUpdate(Model model, @PathVariable Integer id) {
        String status = "";
        BonafideRequest bonafideRequest = bonafideRequestRepo.getReferenceById(id);
        if (bonafideRequest.getStatus() == null || bonafideRequest.getStatus().equals("0")) {
            status = "1";
        } else {
            status = "0";
        }
        bonafideRequestRepo.save(new BonafideRequest(id, bonafideRequest.getStudentId(), bonafideRequest.getFirstname(),
                bonafideRequest.getLastname(), bonafideRequest.getEmail(), bonafideRequest.getStuclass(),
                bonafideRequest.getExtension(), bonafideRequest.getReason(), status));
        List<BonafideRequest> updaterequestrecord = bonafideRequestRepo.findAll();
        model.addAttribute("stulrList", updaterequestrecord);
        return "requestedBonafideRecord";
    }

    @GetMapping("/admin/delete/livingrequest/{id}/")
    public String deleteLeavingRequeste(Model model, @PathVariable Integer id) {
        livingRequestRepo.deleteById(id);
        System.out.println("Deleted record");
        int curPage = 1;
        Pageable pageable = PageRequest.of(curPage, maxSize, Sort.by("id").descending());
        Page<LivingRequest> page = livingRequestRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<LivingRequest> livingRequestList = page.toList();
        List<LivingRequest> livingRequestList1 = livingRequestRepo.findAll();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("stulrList", livingRequestList);
        model.addAttribute("stulrList", livingRequestList1);
        model.addAttribute("recordSize", livingRequestList1.size());
        model.addAttribute("msg", "Record deleted successfully!");
        return "requestedLeavingRecord";
    }

    @GetMapping("/admin/delete/bonafiderequest/{id}/")
    public String deleteBonafideRequeste(Model model, @PathVariable Integer id) {
        int curPage=1;
        bonafideRequestRepo.deleteById(id);
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<BonafideRequest> page = bonafideRequestRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<BonafideRequest> bonafideRequestList = page.toList();
        List<BonafideRequest> bonafideRequestList1 = bonafideRequestRepo.findAll();
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("stulrList", bonafideRequestList);
        model.addAttribute("stulrList", bonafideRequestList1);
        model.addAttribute("recordSize", bonafideRequestList1.size());
        model.addAttribute("msg", "Record deleted successfully!");
        return "requestedBonafideRecord";
    }

    @GetMapping("/admin/bonafidecertificate/{id}/")
    public String displayBonafideCertificate(Model model, @PathVariable Integer id) {
        Admission studentRecord = admissionRepo.getReferenceById(id);
        model.addAttribute("data", studentRecord);
        return "bonafiedceritificate";
    }

    @GetMapping("/admin/admittedstudentstatus/update/{id}/")
    public String updateAdmittedStatus(Model model, @PathVariable Integer id) {
        String status = "";
        Admission studentRecord = admissionRepo.getReferenceById(id);
        if (studentRecord.getStatus() == null || studentRecord.getStatus().equals("0")) {
            status = "1";
        } else {
            status = "0";
        }
        admissionRepo.save(new Admission(studentRecord.getId(), studentRecord.getStuclass(), studentRecord.getFname(),
                studentRecord.getMname(), studentRecord.getLname(), studentRecord.getMotherName(),
                studentRecord.getDob(), studentRecord.getEmailid(), studentRecord.getLastschoolName(),
                studentRecord.getMobileno(), studentRecord.getCountry(), studentRecord.getState(),
                studentRecord.getDistrict(), studentRecord.getHobbies(), studentRecord.getAge(),
                studentRecord.getPercentage(), studentRecord.getExtension(), studentRecord.getSkills(),
                studentRecord.getFqualification(), studentRecord.getMqualification(), studentRecord.getCast(),
                studentRecord.getGender(), studentRecord.getPnumber(), studentRecord.getPeraddress(),
                studentRecord.getTempaddress(), status, studentRecord.getAdmissionDate(),
                studentRecord.getCurrentYear()));
        List<Admission> admissionList = admissionRepo.findAll();
        model.addAttribute("stuList", admissionList);
        return "admissiontable";
    }

    @GetMapping("/admin/delete/admissionrecord/{id}/")
    public String admissionRecordDelete(Model model, @PathVariable Integer id) {
        admissionRepo.deleteById(id);
        List<Admission> admissionList = admissionRepo.findAll();
        model.addAttribute("stuList", admissionList);
        return "admissiontable";
    }

    @GetMapping("/admin/resultupload/")
    public String resultUploadPage() {
        return "adminuploadresult";
    }

    @GetMapping("/admin/importantdate/entry/")
    public String displayImpDatePage() {
        return "adminImportantDateEntryPage";
    }

    @ResponseBody
    @PostMapping("/admin/passdata/")
    public String handlePostRequest(Model model, @RequestBody String data) {
        String arr[] = data.split("=");
        Integer id = Integer.parseInt(arr[0]);
        Admission studentdata = admissionRepo.getReferenceById(id);
        String mothername = studentdata.getMotherName();
        model.addAttribute("studentdata", studentdata);
        return studentdata.toString();
    }
    public static String decodeEmail(String encodedEmail) {
        try {
            String decoded = URLDecoder.decode(encodedEmail, "UTF-8");
            return decoded;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // Handle the exception based on your application's requirements
        }
        return null;
    }
    @PostMapping("/admin/update/password/")
    public String updatePassword(Model model,String email,String password)
    {
        System.out.println("Your password:"+password);
        List<Registration> registrationList=registrationRepo.findByEmail(email);
        Integer id=0;
        String firstname="";
        String lastname="";
        Boolean isAdmin=false;
        String mobilenumber="";
        String extension="";
        for (Registration re:registrationList) {
            id=re.getId();
            firstname=re.getFirstname();
            lastname=re.getLastname();
            mobilenumber=re.getMobilenumber();
            extension=re.getExtension();
            isAdmin=re.getAdmin();
        }
        try{
            registrationRepo.save(new Registration(id,firstname,lastname,email,mobilenumber,password,isAdmin,extension));
            model.addAttribute("msg","Password Updated successfully!");
        }catch (Exception e)
        {
            model.addAttribute("emsg","Password not Updated yet...! Try after some time");
        }
        return "AdminForgetPassword";
    }
    @ResponseBody
    @PostMapping("/admin/send/otp/")
    public String[] submitData(Model model,@RequestBody String data) throws MessagingException, UnsupportedEncodingException {
        String decodedEmail = decodeEmail(data);
        System.out.println(decodedEmail);
        String email[]=decodedEmail.split("=");
        for (String em:email) {
            System.out.println(em);
        }
        List<Registration> registration=registrationRepo.findByEmail(email[0]);
        System.out.println("Registration"+registration);
        String redirectpage="";
        String characters = "0123456789";
        int length = 4;

        // Create an instance of the Random class
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        // Generate the password by selecting random characters
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        System.out.println("Otp"+password);
        System.out.println(!registration.isEmpty());
        if(!registration.isEmpty())
        {
            String from = "ad.developer@gmail.com";
            String to = email[0];
            MimeMessage message=mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String maiSubject="Reset Your Password - [Innovative THings] Account";
            String mailContent = "<h1>Reset Your Password</h1><br>"
                    + "<p>Dear <strong>" + to + "</strong></p>"
                    + "<p>We have received a request to reset the password for your Innovative THings account.</p>"
                    + "<p>Your new temporary password is: <strong>" + password + "</strong></p>"
                    + "<p>Please log in using this password and change it to a new one after signing in.</p>"
                    + "<p>If you didn't request a password reset, please ignore this email.</p>"
                    + "<p>If you have any questions or need further assistance, please don't hesitate to contact our support team at [SupportEmail].</p>"
                    + "<p>Thank you,</p>"
                    + "<p>The Innovative THings Team</p>";
            helper.setFrom(from,"Innovative Things");
            helper.setTo(to);
            helper.setSubject(maiSubject);
            helper.setText(mailContent,true);

            try
            {
                mailSender.send(message);
                System.out.println("Email send successfully");
            }catch (Exception e)
            {
                System.out.println(e);
            }
        }
        else{
            model.addAttribute("emsg","Email is not exist");
        }
        String sendData[]={password.toString()};
        return sendData;

    }

    @ResponseBody
    @PostMapping("/admin/pass/seatcount/")
    public int[] handlePostRequestOfSeatCount(Model model, @RequestBody String data) {
        String arr[] = data.split("=");
        int totalSeat = Integer.parseInt(arr[0]);
        int reOpenCate = totalSeat * 16 / 100;
        int reobcCate = totalSeat * 19 / 100;
        int revjntCate = totalSeat * 11 / 100;
        int restCate = totalSeat * 7 / 100;
        int reewsCate = totalSeat * 10 / 100;
        int retfwsCate = totalSeat * 5 / 100;
        int reserveSeatCount[] = { reOpenCate, reobcCate, revjntCate, restCate, reewsCate, retfwsCate };
        return reserveSeatCount;
    }

    @PostMapping("/admin/cutoff/savedata/")
    public String cutOffSaveData(Model model, String openCat, String obcCat, String vjntCat, String stCat,
            String ewsCat, String tfwsCat) {
        if (session.getAttribute("cutoffId") == null) {
            // Attribute is null, handle accordingly
            try {
                cutoffRepo.save(new CutOff(openCat, obcCat, vjntCat, stCat, ewsCat, tfwsCat));
                model.addAttribute("msg", "Cut off data saved successfully!");
            } catch (Exception e) {
                model.addAttribute("emsg", "This year Cut off data already added!");
            }
        } else {
            Integer id = Integer.parseInt(session.getAttribute("cutoffId").toString());
            CutOff cutOff = cutoffRepo.getReferenceById(id);
            Integer currentYear = cutOff.getCurrentYear();
            try {
                cutoffRepo.save(new CutOff(id, openCat, obcCat, vjntCat, stCat, ewsCat, tfwsCat, currentYear));
                model.addAttribute("msg", "Cut off data updated successfully!");
            } catch (Exception e) {
                model.addAttribute("emsg", "There is some problem while updating the record!");
            }
        }

        session.invalidate();
        return "AdminCutOff";

    }

    @PostMapping("/admin/fees/savedata/")
    public String feesSaveData(Model model, String openCat, String obcCat, String vjntCat, String stCat, String ewsCat,
            String tfwsCat) {
        if (session.getAttribute("feesId") == null) {
            // Attribute is null, handle accordingly
            try {
                feesCriteriaRepo.save(new FeesCriteria(openCat, obcCat, vjntCat, stCat, ewsCat, tfwsCat));
                model.addAttribute("msg", "Fees criteria data saved successfully!");
            } catch (Exception e) {
                model.addAttribute("emsg", "This year fees criteria data already added!");
            }
        } else {
            Integer id = Integer.parseInt(session.getAttribute("feesId").toString());
            CutOff cutOff = cutoffRepo.getReferenceById(id);
            Integer currentYear = cutOff.getCurrentYear();
            try {
                feesCriteriaRepo
                        .save(new FeesCriteria(id, openCat, obcCat, vjntCat, stCat, ewsCat, tfwsCat, currentYear));
                model.addAttribute("msg", "Fees Criteria data updated successfully!");
            } catch (Exception e) {
                model.addAttribute("emsg", "There is some problem while updating the record!");
            }
        }

        session.invalidate();
        return "AdminFeesCriteria";
    }

    @PostMapping("/admin/seatreservation/savedata/")
    public String seatReservationSaveData(Model model, String openCat, String obcCat, String vjntCat, String stCat,
            String ewsCat, String tfwsCat) {
        if (session.getAttribute("seatId") == null) {
            // Attribute is null, handle accordingly
            try {
                seatReservationRepo.save(new SeatReservation(openCat, obcCat, vjntCat, stCat, ewsCat, tfwsCat));
                model.addAttribute("msg", "Seat reservation data saved successfully!");
            } catch (Exception e) {
                model.addAttribute("emsg", "This year Seat reservation data already added!");
            }
        } else {
            Integer id = Integer.parseInt(session.getAttribute("seatId").toString());
            CutOff cutOff = cutoffRepo.getReferenceById(id);
            Integer currentYear = cutOff.getCurrentYear();
            try {
                seatReservationRepo
                        .save(new SeatReservation(id, openCat, obcCat, vjntCat, stCat, ewsCat, tfwsCat, currentYear));
                model.addAttribute("msg", "Seat reservation data updated successfully!");
            } catch (Exception e) {
                model.addAttribute("emsg", "There is some problem while updating the record!");
            }
        }

        session.invalidate();
        return "AdmincastWiseDistribution";
    }

    @PostMapping("/admin/result/upload/")
    public String uploadResult(Model model, String studId, String motherName, String marathi, String hindi,
            String english, String math, String science, String socialsci) {
        if (session.getAttribute("id") == null) {
            resultRepo.save(new Result(studId, motherName, marathi, hindi, english, math, science, socialsci));
            model.addAttribute("msg", "Result uploaded successfully!");

        } else {
            Integer id = Integer.parseInt(session.getAttribute("id").toString());
            resultRepo.save(new Result(id, studId, motherName, marathi, hindi, english, math, science, socialsci));
            model.addAttribute("msg", "Result updated successfully!");
        }
        session.invalidate();
        return "adminuploadresult";
    }

    @PostMapping("/admin/add_new_important_date/")
    public String importantDateEntry(Model model, String title, String date) {
        String redirectPage = "adminImportantDateEntryPage";
        if (session.getAttribute("id") == null) {
            importantDateRepo.save(new ImportantNotification(title, date));
            int curPage = 1;
            Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
            Page<ImportantNotification> page = importantDateRepo.findAll(pageable);
            int totalPages = page.getTotalPages();
            List<ImportantNotification> notificationList = page.toList();
            List<ImportantNotification> importantNotifications = importantDateRepo.findAll();
            model.addAttribute("notificationList", notificationList);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("curPage", curPage);
            model.addAttribute("importantNotifications", importantNotifications);
            model.addAttribute("msg", "New important date added successfully!");
        } else {
            Integer id = Integer.parseInt(session.getAttribute("id").toString());
            importantDateRepo.save(new ImportantNotification(id, title, date));
            int curPage = 1;
            Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
            Page<ImportantNotification> page = importantDateRepo.findAll(pageable);
            int totalPages = page.getTotalPages();
            List<ImportantNotification> notificationList = page.toList();
            List<ImportantNotification> importantNotifications = importantDateRepo.findAll();
            model.addAttribute("notificationList", notificationList);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("curPage", curPage);
            model.addAttribute("importantNotifications", importantNotifications);
            model.addAttribute("msg", "Important date updated successfully!");
            redirectPage = "adminImportantDateRecord";
        }
        session.invalidate();
        return redirectPage;
    }

    @GetMapping("/admin/delete/mportantdaterecord/{id}/")
    public String deleteImportantDateRecord(@PathVariable Integer id, Model model) {
        importantDateRepo.deleteById(id);
        int curPage = 1;
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<ImportantNotification> page = importantDateRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<ImportantNotification> notificationList = page.toList();
        List<ImportantNotification> importantNotifications = importantDateRepo.findAll();
        model.addAttribute("notificationList", notificationList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("importantNotifications", importantNotifications);
        return "adminImportantDateRecord";
    }

    @GetMapping("/admin/viewimportantdate/")
    public String viewImportantDate(Model model) {
        int curPage = 1;
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<ImportantNotification> page = importantDateRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<ImportantNotification> notificationList = page.toList();
        List<ImportantNotification> importantNotifications = importantDateRepo.findAll();
        model.addAttribute("notificationList", notificationList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        model.addAttribute("importantNotifications", importantNotifications);
        return "adminImportantDateRecord";
    }

    @GetMapping("/admin/update/mportantdaterecord/{id}/")
    public String updateDateRecord(Model model, @PathVariable Integer id) {
        session.setAttribute("id", id);
        ImportantNotification importantNotification = importantDateRepo.getReferenceById(id);
        model.addAttribute("data", importantNotification);
        return "adminImportantDateEntryPage";
    }

    @GetMapping("/admin/impdaterecord/{curPage}/")
    public String impdaterecordCurrentPage(@PathVariable int curPage, Model model) {
        Pageable pageable = PageRequest.of(curPage - 1, maxSize, Sort.by("id").descending());
        Page<ImportantNotification> page = importantDateRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<ImportantNotification> notificationList = page.toList();

        model.addAttribute("notificationList", notificationList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        return "adminImportantDateRecord";
    }

    @PostMapping("/admin/new/register/")
    public String adminRegistration(Model model, String firstname, String lastname, String email, String password) {
        Boolean isAdmin = true;
        registrationRepo.save(new Registration(firstname, lastname, email, password, isAdmin));
        model.addAttribute("msg", "Registration done successfully!");
        return "adminregister";
    }

    @PostMapping("/admin/student/aditional/deails/")
    public String studentAditionalDetals(Model model, StudentsAditionalDetails stu) {
        try {
            studentsAditionalDetailsRepo.save(stu);
        } catch (Exception e) {
            model.addAttribute("emsg", "This student additional details was added earlier!");
        }
        List<Admission> admissionList = admissionRepo.findAll();
        model.addAttribute("stuList", admissionList);
        model.addAttribute("msg", "Students additional details added successfully!");
        return "admissiontable";
    }

}
