/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.foyerfiscal.web;

import fr.paris.lutece.plugins.foyerfiscal.api.FoyerFiscalAPI;
import fr.paris.lutece.plugins.foyerfiscal.business.FoyerFiscal;

import fr.paris.lutece.plugins.apipart.business.InformationsFiscales;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import java.util.Map;
import javax.servlet.http.HttpServletRequest; 

/**
 * This class provides the user interface to manage FoyerFiscal xpages ( manage, create, modify, remove )
 */
 
@Controller( xpageName = "foyerfiscal" , pageTitleI18nKey = "foyerfiscal.xpage.foyerfiscal.pageTitle" , pagePathI18nKey = "foyerfiscal.xpage.foyerfiscal.pagePathLabel" )
public class FoyerFiscalXPage extends MVCApplication
{
    // VIEWS
    public static final String VIEW_FOYER_FISCAL = "foyerFiscal";

    // ACTIONS
    public static final String ACTION_FOYER_FISCAL = "foyerFiscal";

    // TEMPLATES
    private static final String TEMPLATE_FOYER_FISCAL = "/skin/plugins/foyerfiscal/foyer-fiscal.html";
    
    // PARAMETERS
    private static final String PARAMETER_NUMERO_FISCAL = "numeroFiscal";
    private static final String PARAMETER_REFERENCE_AVIS = "referenceAvis";
    
    // MARKERS
    private static final String MARK_FOYER_FISCAL = "foyerFiscal";
    private static final String MARK_INFORMATIONS_FISCALES = "informationsFiscales";
    
    //ERRORS
    private static final String ERROR_NUMERO_FISCAL_EMPTY = "apipart.error.numeroFiscal.empty";
    private static final String ERROR_REFERENCE_AVIS_EMPTY = "apipart.error.referenceAvis.empty";
    private static final String ERROR_NUMERO_FISCAL_SIZE = "apipart.error.numeroFiscal.size";
    private static final String ERROR_REFERENCE_AVIS_SIZE = "apipart.error.referenceAvis.size";
    private static final String ERROR_FOYER_FISCAL = "foyerfiscal.error.foyerFiscal";
    
    private static final long serialVersionUID = 1L;
    private FoyerFiscal _foyerFiscal;
    private InformationsFiscales _informationsFiscales;
    private final FoyerFiscalAPI _foyerFiscalAPI = SpringContextService.getBean("foyerfiscal.FoyerFiscalAPI");

    /**
     * Returns the content of the page foyerfiscal.
     * @param request The HTTP request
     * @return The view
     */
    @View( value = VIEW_FOYER_FISCAL, defaultView = true )
    public XPage viewFoyerFiscal( HttpServletRequest request )
    {
        if(_informationsFiscales == null)
        {
            _informationsFiscales = new InformationsFiscales( );
        }
        Map<String, Object> model = getModel(  );
        model.put( MARK_INFORMATIONS_FISCALES, _informationsFiscales );
        model.put( MARK_FOYER_FISCAL , _foyerFiscal );
        return getXPage( TEMPLATE_FOYER_FISCAL, request.getLocale(  ), model );
    }
    
    /**
     * compute the quotient familial by connecting to apipart
     * @param request The HTTP request
     * @return the page foyerfiscal
     */
    @Action( ACTION_FOYER_FISCAL )
    public XPage actionFoyerFiscal( HttpServletRequest request )
    {
        _foyerFiscal = null;
        _informationsFiscales.setNumeroFiscal(request.getParameter( PARAMETER_NUMERO_FISCAL ));
        _informationsFiscales.setReferenceAvis(request.getParameter( PARAMETER_REFERENCE_AVIS ));
        boolean formComplete = true;
                
        if ( _informationsFiscales.getNumeroFiscal().equals("") )
        {
            addError( ERROR_NUMERO_FISCAL_EMPTY, getLocale( request ) );
            formComplete = false;
        }
        else if ( _informationsFiscales.getNumeroFiscal().length() < 13)
        {
            addError( ERROR_NUMERO_FISCAL_SIZE, getLocale( request ) );
            formComplete = false;
        }
        
        if ( _informationsFiscales.getReferenceAvis().equals("") )
        {
            addError( ERROR_REFERENCE_AVIS_EMPTY, getLocale( request ) );
            formComplete = false;
        }
        else if ( _informationsFiscales.getReferenceAvis().length() < 13)
        {
            addError( ERROR_REFERENCE_AVIS_SIZE, getLocale( request ) );
            formComplete = false;
        }
        
        if( !formComplete )
        {
            return redirectView( request, VIEW_FOYER_FISCAL );
        }
        else
        {
            _foyerFiscal = _foyerFiscalAPI.getFoyerFiscal(request, _informationsFiscales);
        
            if ( _foyerFiscal == null )
            {
                addError( ERROR_FOYER_FISCAL, getLocale( request ) );
            }
            return redirectView( request, VIEW_FOYER_FISCAL );
        }
    }
}