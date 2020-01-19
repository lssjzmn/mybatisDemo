package com.example.mydemo.controller;

import com.example.mydemo.core.annotations.SysLog;
import com.example.mydemo.core.bo.WebMessagePayload;
import com.example.mydemo.core.dao.api.DepartmentDaoMapper;
import com.example.mydemo.core.dao.api.DoctorDaoMapper;
import com.example.mydemo.core.dao.api.ParkingRecordMapper;
import com.example.mydemo.core.dao.api.UserRoleDaoMapper;
import com.example.mydemo.core.dao.repository.*;
import com.example.mydemo.core.entity.Department;
import com.example.mydemo.core.entity.Doctor;
import com.example.mydemo.core.entity.ParkingRecord;
import com.example.mydemo.core.entity.UserRole;
import com.example.mydemo.websockets.WebSocketController;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;

@RequestMapping("/TEST")
@Controller
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WebSocketController webSocketController;

    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private SalaryRepo salaryRepo;
    @Autowired
    private PermissionRepo permissionRepo;
    @Autowired
    private UserRoleRepo userRoleRepo;
    @Autowired
    private ParkingRecordRepo parkingRecordRepo;

    @Autowired
    private DepartmentDaoMapper departmentDaoMapper;
    @Autowired
    private DoctorDaoMapper doctorDaoMapper;
    @Autowired
    private UserRoleDaoMapper userRoleDaoMapper;
    @Autowired
    private ParkingRecordMapper parkingRecordMapper;

    @PostConstruct
    public void TestControllerInit() throws Exception {
        logger.info("TestControllerInit...");
        Properties properties = new Properties();
        //InputStream inputStream = new FileInputStream(new File("C:\\cdxtHis\\hisremote.properties"));
        InputStream inputStream = this.getClass().getResourceAsStream("/application.properties");
        properties.load(inputStream);
        properties.setProperty("testPro", "testValue");
        System.out.println(properties.toString());

        List<Doctor> doctors = (List<Doctor>) doctorRepo.findAll();
        List<Doctor> doctorsAged = (List<Doctor>) doctorRepo.findDoctorsByAgeAfter(20);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("clas", "R2");
        List<Department> departments = departmentDaoMapper.findDepartmentsByClas(paramMap);

        List<Integer> rankRange = new ArrayList<>();
        rankRange.add(18);
        rankRange.add(29);
        List<Doctor> doctorList = doctorDaoMapper.findDoctorsByDeptId(departments.get(0), -1, rankRange);

        //int saved = doctorDaoMapper.saveDoctor(newDoctor);
        final Department testDepartment = departments.get(0);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                List<Doctor> saveDoctors = new ArrayList<>();
                for (int i = 0; i < 9999; i++) {
                    String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().substring(0, 20);
                    Doctor newDoctor = new Doctor();
                    newDoctor.setCategory("R9");
                    newDoctor.setAge(33);
                    newDoctor.setDept(testDepartment);
                    newDoctor.setGender("F");
                    newDoctor.setName("H" + i);
                    newDoctor.setRank(21);
                    newDoctor.setId(id);
                    saveDoctors.add(newDoctor);
                }
                long time0 = System.currentTimeMillis();
                int saved = doctorDaoMapper.saveDoctorList(saveDoctors);
                long time1 = System.currentTimeMillis();
                System.out.println("saved " + saved + " doctors cost time " + (time1 - time0));
            }
        }).start();*/
        //List<Permission> permissions = (List<Permission>) permissionRepo.findAll();
        List<UserRole> userRoles = (List<UserRole>) userRoleRepo.findAll();
        List<UserRole> mappedUserRoles = userRoleDaoMapper.findAllUserRoles("X");
        //List<ParkingRecord> parkingRecords = parkingRecordMapper.findParkingRecordsByCarNo("川L8E634", 'I', 'N');
        List<ParkingRecord> parkingRecords = parkingRecordRepo.findParkingRecordsByNativeSql("川L8E634", 'O', 'Y');
        ParkingRecord targetParkingRecord = parkingRecords.get(0);
        String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().substring(0, 20);
        targetParkingRecord.setId(id);
        targetParkingRecord.setCarNo(targetParkingRecord.getCarNo());
        targetParkingRecord.setIoTime(new Date());
        targetParkingRecord.setIoType('O');
        targetParkingRecord.setPayed('Y');
        targetParkingRecord.setParkingName(targetParkingRecord.getParkingName());
        long parkedTime = (System.currentTimeMillis() - targetParkingRecord.getIoTime().getTime()) / 1000L;
        targetParkingRecord.setMoney(8.0F);
        ParkingRecord savedParkingRecord = parkingRecordRepo.save(targetParkingRecord);
        Timer sendTimer = new Timer();
        sendTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                WebMessagePayload payload = new WebMessagePayload();
                payload.setMessageName("my web msg");
                payload.setMessageSerialNo(System.currentTimeMillis() + "");
                payload.setPayload("web msg payload");
                webSocketController.sendWebMessage(payload);
            }
        }, 2000, 1000);
    }

    @SysLog(value = "000")
    public void testSyslogAop(String arg) {
    }

    @SysLog(value = "000")
    @RequestMapping(value = "/req", method = RequestMethod.GET)
    @ResponseBody
    public Object req(@RequestParam(value = "name") String name, HttpServletRequest request) {
        int localPort = request.getLocalPort();
        PageHelper.startPage(1, 1000);
        List<Doctor> doctorList = doctorDaoMapper.findDoctorsByPaging();
        StringBuilder ret = new StringBuilder();
        ret.append(localPort + "");
        for (Doctor doctor : doctorList) {
            ret.append("** " + doctor.toString() + "\n\n");
        }
        return ret;
    }

    @SysLog(value = "访问地址/index.html")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView indexPage(@RequestParam(value = "content") String content, HttpServletRequest request) {
        int localPort = request.getLocalPort();
        PageHelper.startPage(1, 100);
        List<Doctor> doctorList = doctorDaoMapper.findDoctorsByPaging();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("content", content);
        modelAndView.addObject("port", localPort);
        modelAndView.addObject("doctorList", doctorList);
        return modelAndView;
    }

    @RequestMapping(value = "/detail/{detail}", method = RequestMethod.GET)
    public ModelAndView detailPage(@PathVariable String detail) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail");
        modelAndView.addObject("detail", detail);
        return modelAndView;
    }

}
