package com.zzu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzu.bean.Dish;
import com.zzu.bean.Msg;
import com.zzu.service.DishService;

@Controller
public class DishController {
	@Autowired
	private DishService service;

	/**
	 * �������в�Ʒ���б�
	 * 
	 * @return
	 */
	@RequestMapping("/dishes")
	@ResponseBody
	public Msg getDishes() {
		List<Dish> dishes = service.getDishes();
		return Msg.success().add("dishes", dishes);
	}

	@ResponseBody
	@RequestMapping("/dishes/{kind}")
	public Msg getDishByKind(@PathVariable("kind") Integer kind) {
		List<Dish> dishes = service.getDishByKind(kind);
		return Msg.success().add("dishes", dishes);
	}

	/**
	 * ҳ����ת����
	 * 
	 * @return
	 */
	@RequestMapping("/car")
	public String DealCar() {
		return "myCar";
	}

	/**
	 * ��������
	 * 
	 * @param car
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dealCar")
	public Msg dealCar(@RequestParam("car") String car) {

		List<Dish> dishs = new ArrayList<Dish>();
		if (!"".equals(car)) {
			try {
				car = car.substring(0, car.length() - 1);
				String[] cars = car.split(",");

				for (String s : cars) {
					Dish dish = service.getDishById(Integer.parseInt(s));
					dishs.add(dish);
				}
			} catch (Exception e) {
				System.out.println("�����쳣");
			}
		}
		return Msg.success().add("dishs", dishs);
	}

}
