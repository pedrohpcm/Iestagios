/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iEstagios.filtros;

import iEstagios.modelo.Conta;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pc
 */
public class AreaRestritaEstudante implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (session != null && !session.isNew()) {
            Conta conta = (Conta) session.getAttribute("Conta");
            if (conta != null) {
                if (conta.getTipo().equals("Estudante")) {
                    chain.doFilter(request, response);
                }
            } else {
                ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/iEstagios/login.xhtml");
            }
        } else {
            //Retorna para a página de login
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/iEstagios/login.xhtml");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("RestrictedAdminBackoffice:Initializing filter");
            }
        }
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    @Override
    public void destroy() {
        System.out.println("Destruído");
    }
}
