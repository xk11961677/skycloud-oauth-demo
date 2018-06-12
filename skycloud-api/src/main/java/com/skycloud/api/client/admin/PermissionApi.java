package com.skycloud.api.client.admin;

import com.skycloud.common.base.ResponseVo;
import lombok.Data;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sky
 */
@FeignClient(name = "skycloud-admin", fallback = PermissionApi.PermissionApiFallback.class)
public interface PermissionApi {


	@RequestMapping(value="/admin/permission/getMenu",method=RequestMethod.POST)
	@ResponseBody
    ResponseVo<TestDTO> send(@RequestBody TestDTO testDTO);

	/**
	 * 断路器
	 *
	 *
	 */
	@Component
	class PermissionApiFallback  implements PermissionApi {

		@Override
		public ResponseVo send(@RequestBody TestDTO testDTO) {
			return new ResponseVo();
		}



	}
	
	@Data
	class TestDTO {
		String name;
	}

}
