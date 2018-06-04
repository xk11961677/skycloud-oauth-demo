package com.skycloud.api.client.admin;

import com.skycloud.common.base.ResponseData;
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
	ResponseData<TestDTO> send(@RequestBody TestDTO testDTO);

	/**
	 * 断路器
	 *
	 *
	 */
	@Component
	class PermissionApiFallback  implements PermissionApi {

		@Override
		public ResponseData send(@RequestBody TestDTO testDTO) {
			return new ResponseData();
		}



	}
	
	@Data
	class TestDTO {
		String name;
	}

}
