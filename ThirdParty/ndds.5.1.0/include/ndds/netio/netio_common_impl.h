/* (c) Copyright, Real-Time Innovations, Inc. 2003-2009. All rights reserved. */
/*
 * @(#)netio_common_impl.h    generated by: makeheader    Sat Nov 23 02:38:30 2013
 *
 *		built from:	common_impl.ifc
 */

#ifndef netio_common_impl_h
#define netio_common_impl_h


  #include <string.h>

  #ifndef reda_orderedDataType_h
    #include "reda/reda_orderedDataType.h"
  #endif

  #ifndef reda_string_h
    #include "reda/reda_string.h"
  #endif

#ifdef __cplusplus
    extern "C" {
#endif



#define RTINetioAddressFilter_copy(to, src) \
    RTIOsapiMemory_copy(to, src, sizeof(struct RTINetioAddressFilter))

#define RTINetioAddressFilter_equals(l, r) \
    !RTIOsapiMemory_compare(l, r, sizeof(struct RTINetioAddressFilter))

#define RTINetioAddressFilter_filter(f, a) \
    ((f)->address.network_ordered_value[0]  == \
     ((f)->mask.network_ordered_value[0]  & ((a)->network_ordered_value[0] )) && \
     (f)->address.network_ordered_value[1]  == \
     ((f)->mask.network_ordered_value[1]  & ((a)->network_ordered_value[1] )) && \
     (f)->address.network_ordered_value[2]  == \
     ((f)->mask.network_ordered_value[2]  & ((a)->network_ordered_value[2] )) && \
     (f)->address.network_ordered_value[3]  == \
     ((f)->mask.network_ordered_value[3]  & ((a)->network_ordered_value[3] )) && \
     (f)->address.network_ordered_value[4]  == \
     ((f)->mask.network_ordered_value[4]  & ((a)->network_ordered_value[4] )) && \
     (f)->address.network_ordered_value[5]  == \
     ((f)->mask.network_ordered_value[5]  & ((a)->network_ordered_value[5] )) && \
     (f)->address.network_ordered_value[6]  == \
     ((f)->mask.network_ordered_value[6]  & ((a)->network_ordered_value[6] )) && \
     (f)->address.network_ordered_value[7]  == \
     ((f)->mask.network_ordered_value[7]  & ((a)->network_ordered_value[7] )) && \
     (f)->address.network_ordered_value[8]  == \
     ((f)->mask.network_ordered_value[8]  & ((a)->network_ordered_value[8] )) && \
     (f)->address.network_ordered_value[9]  == \
     ((f)->mask.network_ordered_value[9]  & ((a)->network_ordered_value[9] )) && \
     (f)->address.network_ordered_value[10] == \
     ((f)->mask.network_ordered_value[10] & ((a)->network_ordered_value[10])) && \
     (f)->address.network_ordered_value[11] == \
     ((f)->mask.network_ordered_value[11] & ((a)->network_ordered_value[11])) && \
     (f)->address.network_ordered_value[12] == \
     ((f)->mask.network_ordered_value[12] & ((a)->network_ordered_value[12])) && \
     (f)->address.network_ordered_value[13] == \
     ((f)->mask.network_ordered_value[13] & ((a)->network_ordered_value[13])) && \
     (f)->address.network_ordered_value[14] == \
     ((f)->mask.network_ordered_value[14] & ((a)->network_ordered_value[14])) && \
     (f)->address.network_ordered_value[15] == \
     ((f)->mask.network_ordered_value[15] & ((a)->network_ordered_value[15])) )


#define RTINetioAddressFilter_compare(l, r) \
    ( !NDDS_Transport_Address_is_equal(&(l)->address, &(r)->address) ?  1 : \
      !NDDS_Transport_Address_is_equal(&(l)->mask, &(r)->mask) \
     )


#define RTINetioAddressFilter_isSubset(l, r) \
    (((l)->address.network_ordered_value[0] == \
      ((l)->mask.network_ordered_value[0] & ((r)->address.network_ordered_value[0] & \
                               (r)->mask.network_ordered_value[0]))) && \
     ((l)->address.network_ordered_value[1] == \
      ((l)->mask.network_ordered_value[1] & ((r)->address.network_ordered_value[1] & \
                               (r)->mask.network_ordered_value[1]))) && \
     ((l)->address.network_ordered_value[2] == \
      ((l)->mask.network_ordered_value[2] & ((r)->address.network_ordered_value[2] & \
                               (r)->mask.network_ordered_value[2]))) && \
     ((l)->address.network_ordered_value[3] == \
      ((l)->mask.network_ordered_value[3] & ((r)->address.network_ordered_value[3] & \
                               (r)->mask.network_ordered_value[3]))) && \
     ((l)->address.network_ordered_value[4] == \
      ((l)->mask.network_ordered_value[4] & ((r)->address.network_ordered_value[4] & \
                               (r)->mask.network_ordered_value[4]))) && \
     ((l)->address.network_ordered_value[5] == \
      ((l)->mask.network_ordered_value[5] & ((r)->address.network_ordered_value[5] & \
                               (r)->mask.network_ordered_value[5]))) && \
     ((l)->address.network_ordered_value[6] == \
      ((l)->mask.network_ordered_value[6] & ((r)->address.network_ordered_value[6] & \
                               (r)->mask.network_ordered_value[6]))) && \
     ((l)->address.network_ordered_value[7] == \
      ((l)->mask.network_ordered_value[7] & ((r)->address.network_ordered_value[7] & \
                               (r)->mask.network_ordered_value[7]))) && \
     ((l)->address.network_ordered_value[8] == \
      ((l)->mask.network_ordered_value[8] & ((r)->address.network_ordered_value[8] & \
                               (r)->mask.network_ordered_value[8]))) && \
     ((l)->address.network_ordered_value[9] == \
      ((l)->mask.network_ordered_value[9] & ((r)->address.network_ordered_value[9] & \
                               (r)->mask.network_ordered_value[9]))) && \
     ((l)->address.network_ordered_value[10] == \
      ((l)->mask.network_ordered_value[10] & ((r)->address.network_ordered_value[10] & \
                               (r)->mask.network_ordered_value[10]))) && \
     ((l)->address.network_ordered_value[11] == \
      ((l)->mask.network_ordered_value[11] & ((r)->address.network_ordered_value[11] & \
                               (r)->mask.network_ordered_value[11]))) && \
     ((l)->address.network_ordered_value[12] == \
      ((l)->mask.network_ordered_value[12] & ((r)->address.network_ordered_value[12] & \
                               (r)->mask.network_ordered_value[12]))) && \
     ((l)->address.network_ordered_value[13] == \
      ((l)->mask.network_ordered_value[13] & ((r)->address.network_ordered_value[13] & \
                               (r)->mask.network_ordered_value[13]))) && \
     ((l)->address.network_ordered_value[14] == \
      ((l)->mask.network_ordered_value[14] & ((r)->address.network_ordered_value[14] & \
                               (r)->mask.network_ordered_value[14]))) && \
     ((l)->address.network_ordered_value[15] == \
      ((l)->mask.network_ordered_value[15] & ((r)->address.network_ordered_value[15] & \
                               (r)->mask.network_ordered_value[15]))) )

                                              
/* ================================================================= */
/*                    Locator                                        */
/* ================================================================= */
  
#define RTINetioLocator_from(me, transportIn, addressIn, portIn) \
  { (me)->transport = (transportIn); \
    NDDS_Transport_Address_copy(&((me)->address), addressIn); \
    (me)->port = (portIn); }

#define RTINetioLocator_compareWithEncapsulation(left, right) \
  RTINetioLocator_compareI((left), (right), RTI_TRUE)

/* ================================================================= */
/*                    AliasList                                      */
/* ================================================================= */

#define RTINetioAliasList_init(__me) \
    (__me)->element[0] = '\0'

#define RTINetioAliasList_fromString(me, stringValueIn) \
strncpy((me)->element, (stringValueIn), RTI_NETIO_ALIAS_LIST_LENGTH_MAX)


#define RTINetioAliasList_toString(me) ((me)->element)

#define RTINetioAliasList_match(candidateListIn, targetListIn)       \
    (((candidateListIn)->element[0] == 0) ||                         \
     ((targetListIn)->element[0] == 0) ||                            \
      REDAString_hasCommonElement((candidateListIn)->element,        \
                                  (targetListIn)->element,           \
                                  RTI_NETIO_ALIAS_LIST_SEPARATOR))

#define RTINetioAliasList_count(me) \
      REDAString_getTokenCount((me)->element, RTI_NETIO_ALIAS_LIST_SEPARATOR)
                                              
/* ================================================================= */
/*                    LocatorInfo                                    */
/* ================================================================= */

#define RTINetioLocatorInfo_from(me, transportIn, addressIn, portIn, \
                                     priorityIn, stringAliasListIn) \
  { RTINetioLocator_from(&(me)->locator, transportIn, addressIn, portIn); \
    (me)->transport_priority = (priorityIn); \
    RTINetioAliasList_fromString(&((me)->aliasList), stringAliasListIn); }
        
/* ================================================================= */
/*                    Message Received                               */
/* ================================================================= */

/* ================================================================= */



#ifdef __cplusplus
    }	/* extern "C" */
#endif

#endif /* netio_common_impl_h */
