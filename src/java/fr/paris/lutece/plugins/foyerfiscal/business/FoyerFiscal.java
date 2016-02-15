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
package fr.paris.lutece.plugins.foyerfiscal.business;

import fr.paris.lutece.plugins.apipart.business.InformationsClient;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This is the business class for the object FoyerFiscal
 */ 
public class FoyerFiscal extends InformationsClient implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String _strAdresse;
    private Double _dLatitude;
    private Double _dLongitude;

     /**
     * Sets the Adresses
     * @param adresses The Adresses
     */
    @JsonProperty( "adresses" )
    public void setAdresses( Object[] adresses )
    {
        Map<String, Object> adresseGeo = ( Map<String,Object> ) adresses[0];
        Map<String, Object> adresse = ( Map<String,Object> ) adresseGeo.get( "adresse" );
        Map<String, Object> geometry = ( Map<String,Object> ) adresseGeo.get( "geometry" );
        List<Double> _coordinates = ( List<Double> ) geometry.get( "coordinates" );
        
        setAdresse( (String) adresse.get( "label" ) );
        setLongitude( _coordinates.get(0) );
        setLatitude( _coordinates.get(1) );
    }
    
    /**
     * Get the _dLongitude
     * @return the _dLongitude
     */
    public Double getLongitude()
    {
        return _dLongitude;
    }
    
    /**
     * Sets the _dLongitude
     * @param dLongitude The _dLongitude
     */ 
    public void setLongitude( Double dLongitude )
    {
        _dLongitude = dLongitude;
    }
    
    /**
     * Get the _dLatitude
     * @return the _dLatitude
     */
    public Double getLatitude()
    {
        return _dLatitude;
    }
    
    /**
     * Sets the _dLatitude
     * @param dLatitude The _dLatitude
     */ 
    public void setLatitude( Double dLatitude )
    {
        _dLatitude = dLatitude;
    }
    
    /**
     * Returns the _strAdresse
     * @return The _strAdresse
     */
    public String getAdresse( )
    {
        return _strAdresse;
    }

    /**
     * Sets the _strAdresse
     * @param strAdresse The _strAdresse
     */ 
    public void setAdresse( String strAdresse )
    {
        _strAdresse = strAdresse;
    }
}