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
package fr.paris.lutece.plugins.foyerfiscal.api;

import fr.paris.lutece.plugins.foyerfiscal.business.FoyerFiscalList;

import fr.paris.lutece.plugins.apipart.service.MapperService;
import fr.paris.lutece.plugins.apipart.business.InformationsFiscales;
import fr.paris.lutece.plugins.apipart.api.AbstractApiPartAPI;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

/**
 * FoyerFiscalAPI
 */
public class FoyerFiscalAPI extends AbstractApiPartAPI
{
    // PROPERTIES
    private static final String PROPERTY_DOMAIN = "foyerfiscal.domain";
    private static final String PROPERTY_TOKEN = "foyerfiscal.token";
    
    /**
     * Set the FoyerFiscal from the apipart
     * @param request The HTTP request
     * @param informationsFiscales the InformationsFiscales
     * @return the FoyerFiscal
     */
    public FoyerFiscalList getFoyerFiscal( HttpServletRequest request, InformationsFiscales informationsFiscales )
    {
        FoyerFiscalList foyerFiscalList = null;
        try
        {
            String strResponse = buildRequestAPI(informationsFiscales);
            if(strResponse != null)
            {
                foyerFiscalList = MapperService.parse( strResponse, FoyerFiscalList.class );
            }
        }
        catch ( IOException ex )
        {
            AppLogService.error( "Error Foyer Fiscal : " + ex.getMessage(  ), ex );
        }
        return foyerFiscalList;
    }
    
    /**
     * get the Property token
     * @return the Property token
     */
    @Override
    public String getPropertyToken()
    {
        return AppPropertiesService.getProperty(PROPERTY_TOKEN);
    }
    
    /**
     * get the Property domain
     * @return the Property domain
     */
    @Override
    public String getPropertyDomain()
    {
        return AppPropertiesService.getProperty(PROPERTY_DOMAIN);
    }
}