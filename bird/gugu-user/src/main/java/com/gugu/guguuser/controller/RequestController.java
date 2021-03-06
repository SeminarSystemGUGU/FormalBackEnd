package com.gugu.guguuser.controller;

import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.exception.ParamErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gugu.gugumodel.entity.ShareApplicationEntity;
import com.gugu.gugumodel.entity.TeamValidEntity;
import com.gugu.guguuser.service.ShareService;
import com.gugu.guguuser.service.TeamRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author TYJ
 */
@RestController
@RequestMapping("request")
public class RequestController {

    @Autowired
    ShareService shareService;

    @Autowired
    TeamRequestService teamRequestService;

    @Value("${loginPage}")
    String loginPage;
    /**
     * 获得共享讨论课信息
     * @param httpServletRequest
     * @return
     */
    @RolesAllowed({"Teacher","Student"})
    @GetMapping("/seminarshare")
    public ArrayList<Map> getSeminarShareList(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws NotFoundException, IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return shareService.getSeminarShareList(userId);
    }

    /**
     * 修改共享讨论课申请的状态
     * @param requestId
     * @param message
     * @return
     */
    @RolesAllowed("Teacher")
    @PutMapping("/{requestId}/seminarshare")
    public boolean changeSeminarShareStatus(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,@PathVariable("requestId") Long requestId,@RequestBody Map message) throws ParamErrorException, NotFoundException, IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        String handleType=message.get("handleType").toString();
        if(handleType.equals("accept")){
            shareService.acceptSeminarShare(requestId);
        }
        else if(handleType.equals("refuse")){
            shareService.refuseSeminarShare(requestId);
        }
        else{
            throw new ParamErrorException("请求参数错误（必须为accept/refuse）");
        }
        return true;
    }

    /**
     * 获得共享组队信息
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/teamshare")
    @RolesAllowed({"Teacher","Student"})
    public ArrayList<Map> getTeamShareList(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws NotFoundException, IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return shareService.getTeamShareList(userId);
    }

    /**
     * 修改共享分组申请的状态
     * @param requestId
     * @param message
     * @return
     */
    @PutMapping("/{requestId}/teamshare")
    @RolesAllowed("Teacher")
    public boolean changeTeamShareStatus(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,@PathVariable("requestId") Long requestId,@RequestBody Map message) throws ParamErrorException, NotFoundException, IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        String handleType=message.get("handleType").toString();
        return shareService.changeTeamShareStatus(requestId,handleType);
    }

    /**
     * 教师获得组队申请信息
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/teamvalid")
    @RolesAllowed("Teacher")
    public ArrayList<Map> getTeamRequestList(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws NotFoundException, IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        Long teacherId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return teamRequestService.getTeamRequestList(teacherId);
    }

    /**
     * 修改小组申请的状态
     * @param requestId
     * @param message
     * @return
     */
    @RolesAllowed("Teacher")
    @PutMapping("/{requestId}/teamvalid")
    public boolean changeTeamRequestStatus(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,@PathVariable("requestId") Long requestId,@RequestBody Map message) throws ParamErrorException, IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        String handleType=message.get("handleType").toString();
        return teamRequestService.changeTeamRequestStatus(requestId,handleType);
    }
}
