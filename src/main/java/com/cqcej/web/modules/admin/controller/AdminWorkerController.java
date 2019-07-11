package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.entity.AdminBankCardEntity;
import com.cqcej.web.modules.admin.entity.AdminPickUpEntity;
import com.cqcej.web.modules.admin.entity.AdminUserEntity;
import com.cqcej.web.modules.admin.service.AdminWorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;


/**
 * 工作者(接送人员)
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-27 11:23:32
 */
@RestController
@RequestMapping("admin/worker")
@Api(description = "Admin接送人员管理接口")
public class AdminWorkerController {
	@Autowired
	private AdminWorkerService workerService;
	
	/**
	 * 接送人员信息
	 */
	@GetMapping("/list")
	@RequiresPermissions("admin:worker:list")
	@ApiOperation("接送人员信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "workerStatus", value = "人员状态", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "workerId", value = "人员ID", required = false, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "realname", value = "人员名称", required = false, dataType = "string", paramType = "query")
	})
	public R<AdminPickUpEntity> list(@ApiIgnore @RequestParam Map<String, Object> params) {
		PageUtils<AdminPickUpEntity> data = workerService.selectPickUp(params);
		return R.ok(data);
	}
	
	/**
	 * 地图显示
	 */
	@GetMapping("/info")
	@RequiresPermissions("admin:worker:info")
	@ApiOperation("地图显示")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "条数", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "是否在线(0离线，1在线)", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "realname", value = "姓名", required = false, dataType = "string", paramType = "query")
	})
	public R<PageUtils<AdminPickUpEntity>> info(@RequestParam Integer status,
	                                            @RequestParam Integer page,
	                                            @RequestParam Integer size,
	                                            @RequestParam(required = false) String realname) {
		PageUtils<AdminPickUpEntity> data = workerService.selectWorkerLocation(page, size, status, realname);
		return R.ok(data);
	}
	
	/**
	 * 接送人员信息(基本信息)
	 */
	@GetMapping("/getworker/{workerId}")
	@RequiresPermissions("admin:worker:getworker")
	@ApiOperation("人员编辑(基本信息)")
	@ApiImplicitParam(name = "workerId", value = "人员ID", required = true, dataType = "long", paramType = "query")
	public R<AdminUserEntity> getPickUp(@PathVariable("workerId") Long workerId) {
		AdminUserEntity data = workerService.getPickUp(workerId);
		return R.ok(data);
	}
	
	/**
	 * 详细信息
	 */
	@GetMapping("/detail/{workerId}")
	@RequiresPermissions("admin:worker:detail")
	@ApiOperation("人员编辑(详细信息)")
	@ApiImplicitParam(name = "workerId", value = "人员ID", required = true, dataType = "long", paramType = "query")
	public R<List<AdminBankCardEntity>> detail(@PathVariable("workerId") Long workerId) {
		List<AdminBankCardEntity> data = workerService.detail(workerId);
		return R.ok(data);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("admin:worker:save")
	public R save(@RequestBody AdminUserEntity worker) {
		try {
			worker.setPassword(DigestUtils.sha256Hex(worker.getPassword()));
			return R.ok(workerService.insertWorker(worker));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
		
	}
	
	/**
	 * 人员编辑
	 */
	@PostMapping("/update")
	@RequiresPermissions("admin:worker:update")
	@ApiOperation("人员编辑(修改信息)")
	public R update(@RequestBody AdminUserEntity user) {
		try {
			return R.ok(workerService.updateWorkerById(user));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("系统繁忙");
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("admin:worker:delete")
	public R delete(@RequestBody Long[] workerIds) {
		//workerService.deleteBatchIds(Arrays.asList(workerIds));
		return R.ok();
	}
	
}
