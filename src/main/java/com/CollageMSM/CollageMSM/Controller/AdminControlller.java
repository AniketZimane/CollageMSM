package com.CollageMSM.CollageMSM.Controller;

import com.CollageMSM.CollageMSM.Dao.AdmissionRepo;
import com.CollageMSM.CollageMSM.Dao.BonafideRequestRepo;
import com.CollageMSM.CollageMSM.Dao.LivingRequestRepo;
import com.CollageMSM.CollageMSM.Dao.ResultRepo;
import com.CollageMSM.CollageMSM.Entity.Admission;
import com.CollageMSM.CollageMSM.Entity.BonafideRequest;
import com.CollageMSM.CollageMSM.Entity.LivingRequest;
import com.CollageMSM.CollageMSM.Entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Strings;

import java.time.LocalDate;
import java.util.List;



@Controller
public class AdminControlller
{
    @Autowired
    AdmissionRepo admissionRepo;
    @Autowired
    LivingRequestRepo livingRequestRepo;
    @Autowired
    BonafideRequestRepo bonafideRequestRepo;
    @Autowired
    ResultRepo resultRepo;

    @GetMapping("/admin/dashboard/")
    public String adminDashboard(Model model)
    {
        List<LivingRequest> livingRequestList=livingRequestRepo.findAll();
        List<Admission> admissionformfillList=admissionRepo.findAll();
        List<BonafideRequest> bonafideRequestList=bonafideRequestRepo.findAll();
        Integer stucount=admissionformfillList.size();
        Integer livingRequestCount=livingRequestList.size();
        Integer bonafideRequestCount=bonafideRequestList.size();
        System.out.println("No of record:"+stucount);
        model.addAttribute("count",stucount);
        model.addAttribute("livingRequestCount",livingRequestCount);
        model.addAttribute("bonafideRequestCount",bonafideRequestCount);
        return "admidsashboard";
    }
    @GetMapping("/admin/leavingrequest/record/")
    public String leavingrequest(Model model)
    {
        List<LivingRequest> livingRequestList=livingRequestRepo.findAll();
        model.addAttribute("stulrList",livingRequestList);
        return "requestedLeavingRecord";
    }

    @GetMapping("/admin/bonafideRequest/record/")
    public String bonafideRequest(Model model)
    {
        List<BonafideRequest> bonafideRequestList=bonafideRequestRepo.findAll();
        model.addAttribute("stulrList",bonafideRequestList);
        return "requestedBonafideRecord";
    }

    @GetMapping("/admin/admissinrecord/")
    public String admssionRecord(Model model)
    {
        List<Admission> admissionformfillList=admissionRepo.findAll();
        model.addAttribute("stuList",admissionformfillList);
        return "admissiontable";
    }

    @GetMapping("/admin/leavingrequest/update/{id}/")
    public String leavingRequestUpdate(Model model, @PathVariable Integer id)
    {
        String status="";
        LivingRequest livingRequestrecord=livingRequestRepo.getReferenceById(id);
        if(livingRequestrecord.getStatus()==null || livingRequestrecord.getStatus().equals("0"))
        {
            status="1";
        }
        else{
            status="0";
        }
        livingRequestRepo.save(new LivingRequest(id,livingRequestrecord.getStudentId(),livingRequestrecord.getFirstname(),livingRequestrecord.getLastname(),livingRequestrecord.getEmail(),livingRequestrecord.getStuclass(),livingRequestrecord.getExtension(),livingRequestrecord.getReason(),status));
        System.out.println("data saved");
        List<LivingRequest> updaterequestrecord=livingRequestRepo.findAll();
        model.addAttribute("stulrList",updaterequestrecord);
        return "requestedLeavingRecord";
    }
    @GetMapping("/admin/bonafiderequest/update/{id}/")
    public String bonafideRequestUpdate(Model model, @PathVariable Integer id)
    {
        String status="";
        BonafideRequest bonafideRequest=bonafideRequestRepo.getReferenceById(id);
        if(bonafideRequest.getStatus()==null || bonafideRequest.getStatus().equals("0"))
        {
            status="1";
        }
        else{
            status="0";
        }
        bonafideRequestRepo.save(new BonafideRequest(id,bonafideRequest.getStudentId(),bonafideRequest.getFirstname(),bonafideRequest.getLastname(),bonafideRequest.getEmail(),bonafideRequest.getStuclass(),bonafideRequest.getExtension(),bonafideRequest.getReason(),status));
        System.out.println("data saved");
        List<BonafideRequest> updaterequestrecord=bonafideRequestRepo.findAll();
        model.addAttribute("stulrList",updaterequestrecord);
        return "requestedBonafideRecord";
    }
    @GetMapping("/admin/delete/livingrequest/{id}/")
    public String deleteLeavingRequeste(Model model,@PathVariable Integer id)
    {
        livingRequestRepo.deleteById(id);
        System.out.println("Deleted record");
        List<LivingRequest> updaterequestrecord=livingRequestRepo.findAll();
        model.addAttribute("stulrList",updaterequestrecord);
        return "requestedLeavingRecord";
    }
    @GetMapping("/admin/delete/bonafiderequest/{id}/")
    public String deleteBonafideRequeste(Model model,@PathVariable Integer id)
    {
        bonafideRequestRepo.deleteById(id);
        System.out.println("Deleted record");
        List<BonafideRequest> updaterequestrecord=bonafideRequestRepo.findAll();
        model.addAttribute("stulrList",updaterequestrecord);
        return "requestedBonafideRecord";
    }
    @GetMapping("/admin/bonafidecertificate/{id}/")
    public String displayBonafideCertificate(Model model,@PathVariable Integer id)
    {
        String status="1";
//        List<Admission> studentRecord=admissionRepo.findByIdAndStatus(id,status);
//        System.out.println("Requet is not full filled");
        Admission studentRecord=admissionRepo.getReferenceById(id);
        model.addAttribute("data",studentRecord);
        return "bonafiedceritificate";
    }

    @GetMapping("/admin/admittedstudentstatus/update/{id}/")
    public String updateAdmittedStatus(Model model,@PathVariable Integer id)
    {
        String status="";
        Admission studentRecord=admissionRepo.getReferenceById(id);
        if(studentRecord.getStatus()==null || studentRecord.getStatus().equals("0"))
        {
            status="1";
        }
        else{
            status="0";
        }
        admissionRepo.save(new Admission(studentRecord.getId(),studentRecord.getStuclass(),studentRecord.getFname(),studentRecord.getMname(),studentRecord.getLname(),studentRecord.getMotherName(),studentRecord.getDob(),studentRecord.getEmailid(),studentRecord.getLastschoolName(),studentRecord.getMobileno(),studentRecord.getCountry(),studentRecord.getState(),studentRecord.getDistrict(),studentRecord.getHobbies(),studentRecord.getAge(),studentRecord.getPercentage(),studentRecord.getExtension(),studentRecord.getSkills(),studentRecord.getFqualification(),studentRecord.getMqualification(),studentRecord.getCast(),studentRecord.getGender(),studentRecord.getPnumber(),studentRecord.getPeraddress(),studentRecord.getTempaddress(),status,studentRecord.getAdmissionDate(),studentRecord.getCurrentYear()));
        List<Admission> admissionList=admissionRepo.findAll();
        model.addAttribute("stuList",admissionList);
        return "admissiontable";
    }
    @GetMapping("/admin/delete/admissionrecord/{id}/")
    public String admissionRecordDelete(Model model,@PathVariable Integer id)
    {
        admissionRepo.deleteById(id);
        List<Admission> admissionList=admissionRepo.findAll();
        model.addAttribute("stuList",admissionList);
        return "admissiontable";
    }

    @GetMapping("/admin/resultupload/")
    public String resultUploadPage()
    {
        return "adminuploadresult";
    }

    @ResponseBody
    @PostMapping("/admin/passdata/")
    public String handlePostRequest(Model model,@RequestBody String data) {
        String arr[]=data.split("=");
        Integer id=Integer.parseInt(arr[0]);
        Admission studentdata=admissionRepo.getReferenceById(id);
        String mothername=studentdata.getMotherName();
        model.addAttribute("studentdata",studentdata);
        return studentdata.toString();
    }
    @PostMapping("/admin/result/upload/")
    public String uploadResult(Model model, Result result)
    {
        resultRepo.save(result);
        return "adminuploadresult";
    }
}
