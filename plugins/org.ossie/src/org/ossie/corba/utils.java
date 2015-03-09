/*
 * This file is protected by Copyright. Please refer to the COPYRIGHT file 
 * distributed with this source distribution.
 * 
 * This file is part of REDHAWK core.
 * 
 * REDHAWK core is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your 
 * option) any later version.
 * 
 * REDHAWK core is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

package org.ossie.corba;
import java.util.Properties;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POAManagerPackage.*;
import org.omg.PortableServer.POAPackage.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class utils {

    public static byte[]  activateObject (  Servant servant, 
                                            byte[] identifier ) {
        return activateObject( OrbContext.RootPOA(), servant, identifier );
    }


   public static byte[]  activateObject ( POA poa, 
                                          Servant servant, 
                                          byte[] identifier )
    {
        try {
            if (identifier == null || identifier.length == 0 ) {
                try {
                    return poa.activate_object(servant);
                }
                catch( ServantAlreadyActive e ) {
                }


                try{ 
                    return poa.servant_to_id(servant);
                }
                catch( ServantNotActive e ) {
                }
            }

            try {
                poa.activate_object_with_id(identifier, servant);
            }
            catch( ServantAlreadyActive e ) {
            }
            catch( ObjectAlreadyActive e ) {
            }

            try{ 
                return poa.servant_to_id(servant);
            }
            catch( ServantNotActive e ) {
            }
        }
        catch( WrongPolicy e ) {
        }

        return null;
    }


    public static void deactivateObject ( Servant obj ) {
        try {
            OrbContext _orb = OrbContext.Get();
        
            if  ( _orb != null && _orb.rootPOA != null ) {
                  byte [] oid = _orb.rootPOA.servant_to_id(obj);
                  _orb.rootPOA.deactivate_object(oid);
            }
        }
        catch( ServantNotActive e ) {
        }
        catch( ObjectNotActive e ) {
        }
        catch( WrongPolicy e ) {
        }
    }


    public static ORB  GetOrb() {
        return OrbContext.GetOrb();
    }

    public static POA RootPOA() {
        return OrbContext.RootPOA();
    }

    public static NamingContext NamingService() {
        return OrbContext.NamingService();
    }

    public static boolean objectExists( org.omg.CORBA.Object obj) {
        try {
            return (obj != null && !obj._non_existent());
        } catch ( OBJECT_NOT_EXIST e ) {
                return false;
        }
    }

    
    public static class OrbContext {

        org.omg.CORBA.ORB orb = null;

        POA               rootPOA = null;

        NamingContextExt  namingService = null;


        private static  OrbContext   _singleton = null;

        public static void Init ( ORB orb, POA poa, NamingContextExt ns ) {

            if ( _singleton == null ) {
                _singleton = new OrbContext(orb, poa, ns);
            }
        }

        public static OrbContext Get() {
            return _singleton;
        }

        public static ORB GetOrb() {
            if ( _singleton != null ) {
                return _singleton.orb;
            }
            else {
                return null;
            }
        }

        public static POA RootPOA() {
            if ( _singleton != null ) {
                return _singleton.rootPOA;
            }
            else {
                return null;
            }
        }

        public static NamingContextExt NamingService() {
            if ( _singleton != null ) {
                return _singleton.namingService;
            }
            else {
                return null;
            }
        }
            
        private OrbContext(ORB orb, POA poa, NamingContextExt ns ) {
            orb=orb;
            rootPOA=poa;
            namingService = ns;
        }
        
    }
}
