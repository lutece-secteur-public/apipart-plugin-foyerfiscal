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

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This is the business class for the object QuotientFamilial
 */ 
public class FoyerFiscal implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @JsonProperty("adresse")
    private AdresseComplete _adresseComplete = new AdresseComplete();
    
    @JsonProperty("geometry")
    private Geometry _geometry = new Geometry();

    /**
     * Returns the _adresseComplete
     * @return The _adresseComplete
     */
    public AdresseComplete getAdresseComplete( )
    {
        return _adresseComplete;
    }

    /**
     * Sets the _adresseComplete
     * @param adresseComplete The _adresseComplete
     */ 
    public void setAdresseComplete( AdresseComplete adresseComplete )
    {
        _adresseComplete = adresseComplete;
    }
    
    /**
     * Returns the _geometry
     * @return The _geometry
     */
    public Geometry getGeometry( )
    {
        return _geometry;
    }

    /**
     * Sets the _geometry
     * @param geometry The _geometry
     */ 
    public void setGeometry( Geometry geometry )
    {
        _geometry = geometry;
    }
    
    /**
     * Get the adresse
     * @return the adresse
     */
    public String getAdresse()
    {
        return getAdresseComplete().getAdresse();
    }
    
    /**
     * Get the type of geometry
     * @return the type of geometry
     */
    public String getType()
    {
        return getGeometry().getType();
    }
    
    /**
     * Get the longitude
     * @return the longitude
     */
    public Double getLongitude()
    {
        return getGeometry().getCoordinate().get(0);
    }
    
    /**
     * Get the latitude
     * @return the latitude
     */
    public Double getLatitude()
    {
        return getGeometry().getCoordinate().get(1);
    }
}
