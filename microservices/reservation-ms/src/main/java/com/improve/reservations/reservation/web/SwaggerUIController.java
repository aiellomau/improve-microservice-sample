/**
 * @author maiello
 *
 *         Create on Aug 26, 2019
 */
package com.improve.reservations.reservation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 */
@Controller
public class SwaggerUIController {

	@RequestMapping(value = "/swagger")
	public String index() {

		return "redirect:swagger-ui.html";
	}
}
