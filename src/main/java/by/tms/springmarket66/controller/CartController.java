package by.tms.springmarket66.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import by.tms.springmarket66.dto.CartOfGoodsDTO;
import by.tms.springmarket66.entity.Goods;
import by.tms.springmarket66.mapper.CartMapper;
import by.tms.springmarket66.service.CartOfGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "cart")
public class CartController {
    private final CartOfGoodsService cartOfGoodsService;
    private final CartMapper cartMapper;

    public CartController(CartOfGoodsService cartOfGoodsService, CartMapper cartMapper) {
        this.cartOfGoodsService = cartOfGoodsService;
        this.cartMapper = cartMapper;
    }


    @GetMapping(value = "index")
    public String index() {
        return "cart/index";
    }

    @GetMapping(value = "/buy/{id}")
    public String buy(@PathVariable("id") Long id, HttpSession session) {
        CartOfGoodsDTO cartOfGoodsDTO = new CartOfGoodsDTO();
        if (session.getAttribute("cart") == null) {
            List<Goods> cart = new ArrayList<Goods>();
            cart.add(new Goods(cartOfGoodsDTO.find(id), 1));
            session.setAttribute("cart", cart);
        } else {
            List<Goods> cart = (List<Goods>) session.getAttribute("cart");
            int index = this.exists(Long.valueOf(String.valueOf(id)), cart);
            if (index == -1) {
                cart.add(new Goods(cartOfGoodsDTO.find(id), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart/index";
    }

    @GetMapping(value = "/remove/{id}")
    public String remove(@PathVariable("id") Long id, HttpSession session) {
        CartOfGoodsDTO cartOfGoodsDTO = new CartOfGoodsDTO();
        List<Goods> cart = (List<Goods>) session.getAttribute("cart");
        int index = this.exists(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "redirect:/cart/index";
    }

    private int exists(Long id, List<Goods> goodsList) {
        for (int i = 0; i < goodsList.size(); i++) {
            if (goodsList.get(i).getQuantity().getId(String.valueOf(id)).equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

}
