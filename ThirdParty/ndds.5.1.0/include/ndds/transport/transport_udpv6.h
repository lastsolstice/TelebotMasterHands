/* (c) Copyright, Real-Time Innovations, Inc. 2003-2009. All rights reserved. */
/*
 * @(#)transport_udpv6.h    generated by: makeheader    Sat Nov 23 02:38:25 2013
 *
 *		built from:	udpv6.ifc
 */

#ifndef transport_udpv6_h
#define transport_udpv6_h


  #ifndef transport_dll_h
    #include "transport/transport_dll.h"
  #endif
  #ifndef transport_interface_h
    #include "transport/transport_interface.h"
  #endif

#ifdef __cplusplus
    extern "C" {
#endif

struct RTIClock;

struct NDDS_Transport_UDPv6; /* forward declare */

struct NDDS_Transport_UDPv6_SocketFactory;

struct NDDS_Transport_UDPv6_Property_t {

    /*e \dref_UDPv6Transport_Property_t_parent
     */
    struct NDDS_Transport_Property_t parent;

    /*e \dref_UDPv6Transport_Property_t_send_socket_buffer_size
     */
    RTI_INT32 send_socket_buffer_size;

    /*e \dref_UDPv6Transport_Property_t_recv_socket_buffer_size
    */
    RTI_INT32 recv_socket_buffer_size;

    /*e \dref_UDPv6Transport_Property_t_unicast_enabled
     */
    RTI_INT32 unicast_enabled;

    /*e \dref_UDPv6Transport_Property_t_multicast_enabled
     */
    RTI_INT32 multicast_enabled;

    /*e \dref_UDPv6Transport_Property_t_multicast_ttl
    */
    RTI_INT32 multicast_ttl;

    /*e \dref_UDPv6Transport_Property_t_multicast_loopback_disabled
    */
    RTI_INT32 multicast_loopback_disabled;

    /*e \dref_UDPv6Transport_Property_t_ignore_loopback_interface
    */
    RTI_INT32 ignore_loopback_interface;

    /*e \dref_UDPv6Transport_Property_t_ignore_nonrunning_interfaces
    */
    RTI_INT32 ignore_nonrunning_interfaces;
    
    /*e \dref_UDPv6Transport_Property_t_no_zero_copy
    */
    RTI_INT32 no_zero_copy;

    /*e \dref_UDPv6Transport_Property_t_send_blocking
    */
    RTI_INT32 send_blocking;

    /*e \dref_UDPv6Transport_Property_t_enable_v4mapped
    */
    RTI_INT32 enable_v4mapped;

    /*e \dref_UDPv6Transport_Property_t_transport_priority_mask
    */
    RTI_UINT32 transport_priority_mask;

    /*e \dref_UDPv6Transport_Property_t_transport_priority_mapping_low
    */
    RTI_INT32 transport_priority_mapping_low;

    /*e \dref_UDPv6Transport_Property_t_transport_priority_mapping_high
    */
    RTI_INT32 transport_priority_mapping_high;
};


#define NDDS_TRANSPORT_UDPV6_CLASS_NAME         "udpv6"


#define NDDS_TRANSPORT_UDPV6_ADDRESS_BIT_COUNT  (128)


#define NDDS_TRANSPORT_UDPV6_PROPERTIES_BITMAP_DEFAULT (0)


#define NDDS_TRANSPORT_UDPV6_GATHER_SEND_BUFFER_COUNT_MAX_DEFAULT (16)


#define NDDS_TRANSPORT_UDPV6_SOCKET_BUFFER_SIZE_OS_DEFAULT (-1)


#define NDDS_TRANSPORT_UDPV6_SEND_SOCKET_BUFFER_SIZE_DEFAULT (131072)


#define NDDS_TRANSPORT_UDPV6_RECV_SOCKET_BUFFER_SIZE_DEFAULT (131072)


#define NDDS_TRANSPORT_UDPV6_MESSAGE_SIZE_MAX_DEFAULT (65507)


#define NDDS_TRANSPORT_UDPV6_MULTICAST_TTL_DEFAULT (1)


#ifdef RTI_MULTICAST
  #define NDDS_TRANSPORT_UDPV6_USE_MULTICAST_DEFAULT 1
#else
  #define NDDS_TRANSPORT_UDPV6_USE_MULTICAST_DEFAULT 0
#endif


/*e \dref_UDPv6Transport_BLOCKING_NEVER
 */
  #define NDDS_TRANSPORT_UDPV6_BLOCKING_NEVER        (0)

/*e \dref_UDPv6Transport_BLOCKING_ALWAYS
 */
  #define NDDS_TRANSPORT_UDPV6_BLOCKING_ALWAYS       (1)
  #define NDDS_TRANSPORT_UDPV6_BLOCKING_UNICAST_ONLY (2)

  #define NDDS_TRANSPORT_UDPV6_BLOCKING_DEFAULT \
            NDDS_TRANSPORT_UDPV6_BLOCKING_ALWAYS


#define NDDS_TRANSPORT_UDPV6_PROPERTY_DEFAULT { \
    { NDDS_TRANSPORT_CLASSID_UDPv6, \
        NDDS_TRANSPORT_UDPV6_ADDRESS_BIT_COUNT, \
        NDDS_TRANSPORT_UDPV6_PROPERTIES_BITMAP_DEFAULT, \
        NDDS_TRANSPORT_UDPV6_GATHER_SEND_BUFFER_COUNT_MAX_DEFAULT, \
        NDDS_TRANSPORT_UDPV6_MESSAGE_SIZE_MAX_DEFAULT, \
        NULL, 0, /* allow_interfaces_list */ \
        NULL, 0, /* deny_interfaces_list */ \
        NULL, 0, /* allow_multicast_interfaces_list */ \
        NULL, 0, /* deny_multicast_interfaces_list */ \
    }, \
    NDDS_TRANSPORT_UDPV6_SEND_SOCKET_BUFFER_SIZE_DEFAULT, \
    NDDS_TRANSPORT_UDPV6_RECV_SOCKET_BUFFER_SIZE_DEFAULT, \
    1, /* use unicast */   \
    NDDS_TRANSPORT_UDPV6_USE_MULTICAST_DEFAULT, /* use multicast */ \
    NDDS_TRANSPORT_UDPV6_MULTICAST_TTL_DEFAULT, \
    0, /* multicast loopback enabled */ \
    -1, /* (auto-)ignore loopback */ \
    0, /* do not ignore non-RUNNING */ \
    0, /* no_zero_copy */ \
    NDDS_TRANSPORT_UDPV6_BLOCKING_DEFAULT, \
    0,  /* enable_v4mapped */ \
    0, 0, 0xff /* no mapping to IPV6_TCLASS by default */ }

struct NDDS_Transport_UDPv6_InterfaceListener;/*forward declare */

typedef 
RTI_INT32 (*NDDS_Transport_UDPv6_Interface_Filter_Fcn)(
    struct NDDS_Transport_UDPv6_InterfaceListener* self,
    struct NDDS_Transport_Property_t *plugin_properties,
    RTI_INT32                        *multicast_enabled_out,
    const NDDS_Transport_Interface_t *interface_in,
    const char*                       interface_name_in);

struct NDDS_Transport_UDPv6_InterfaceListener {
    /* \copydoc NDDS_Transport_UDPv6_Interface_Filter_Fcn
     */
    NDDS_Transport_UDPv6_Interface_Filter_Fcn onInterface;
};

extern NDDS_Transport_DllExport 
RTI_INT32 NDDS_Transport_UDPv6_InterfaceListener_onInterface(
    struct NDDS_Transport_UDPv6_InterfaceListener* self,
    struct NDDS_Transport_Property_t *plugin_properties,
    RTI_INT32                        *multicast_enabled_out,
    const NDDS_Transport_Interface_t *interface_in,
    const char*                       interface_name_in);

extern NDDS_Transport_DllVariable
struct NDDS_Transport_UDPv6_InterfaceListener
         NDDS_Transport_UDPv6_g_interfaceListener;

struct NDDS_Transport_UDPv6_SocketFactory;

typedef RTI_INT32
(*NDDS_Transport_UDPv6_SocketFactory_Send_Socket_Create_Fcn)(
    struct NDDS_Transport_UDPv6_SocketFactory *me,
    const struct NDDS_Transport_UDPv6         *transport_in,
    const unsigned int			      *multicast_interface_in);


typedef RTI_INT32 (*NDDS_Transport_UDPv6_SocketFactory_Receive_Socket_Create_Fcn)(
    struct NDDS_Transport_UDPv6_SocketFactory *me,
    NDDS_Transport_Port_t                     *port_inout,
    const struct NDDS_Transport_UDPv6         *transport_in, 
    const NDDS_Transport_Address_t            *multicast_address_in, 
    const unsigned int			      *multicast_interface_in);


typedef RTI_INT32 (*NDDS_Transport_UDPv6_SocketFactory_Socket_Destroy_Fcn)(
    struct NDDS_Transport_UDPv6_SocketFactory *me, 
    RTI_INT32                                  s_in);

struct NDDS_Transport_UDPv6_SocketFactory {

    /*e Children: implement this method. */
    NDDS_Transport_UDPv6_SocketFactory_Send_Socket_Create_Fcn    create_send_socket;

    /*e Children: implement this method. */
    NDDS_Transport_UDPv6_SocketFactory_Receive_Socket_Create_Fcn create_receive_socket;

    /*e Children: implement this method to destroy both the send and
     receive sockets. */
    NDDS_Transport_UDPv6_SocketFactory_Socket_Destroy_Fcn        destroy_socket;
};


#define NDDS_Transport_UDPv6_SocketFactory_is_valid(m) ( \
       (m)->create_send_socket    && \
       (m)->create_receive_socket && \
       (m)->destroy_socket )

extern NDDS_Transport_DllExport 
RTI_INT32 NDDS_Transport_UDPv6_SocketFactory_create_send_socket(
    struct NDDS_Transport_UDPv6_SocketFactory *me,
    const struct NDDS_Transport_UDPv6         *transport_in, 
    const unsigned int			      *multicast_interface_in);

extern NDDS_Transport_DllExport 
RTI_INT32 NDDS_Transport_UDPv6_SocketFactory_create_receive_socket(
    struct NDDS_Transport_UDPv6_SocketFactory *me,
    NDDS_Transport_Port_t                     *port_inout, 
    const struct NDDS_Transport_UDPv6         *transport_in,
    const NDDS_Transport_Address_t            *multicast_address_in, 
    const unsigned int			      *multicast_interface_in);

extern NDDS_Transport_DllExport 
RTI_INT32 NDDS_Transport_UDPv6_SocketFactory_destroy_socket(
    struct NDDS_Transport_UDPv6_SocketFactory *me,
    RTI_INT32                                  s_in);

extern NDDS_Transport_DllExport 
NDDS_Transport_Port_t NDDS_Transport_UDPv6_Socket_bind(
    RTI_INT32                   s_in, 
    const NDDS_Transport_Port_t port_desired_in);

extern NDDS_Transport_DllVariable
struct NDDS_Transport_UDPv6_SocketFactory NDDS_Transport_UDPv6_g_socketFactory;

extern NDDS_Transport_DllExport 
NDDS_Transport_Plugin *NDDS_Transport_UDPv6_new(
    const struct NDDS_Transport_UDPv6_Property_t *property_in);

extern NDDS_Transport_DllExport 
NDDS_Transport_Plugin *NDDS_Transport_UDPv6_newI(
    const struct NDDS_Transport_UDPv6_Property_t *property_in,
    struct NDDS_Transport_UDPv6_SocketFactory    *socket_factory_in,
    struct NDDS_Transport_UDPv6_InterfaceListener *interface_listener_in,
    struct RTIClock                              *clock_in);

extern NDDS_Transport_DllExport 
const char * NDDS_Transport_UDPv6_get_class_name_cEA(
    NDDS_Transport_Plugin    *self);

extern NDDS_Transport_DllExport 
RTI_INT32 NDDS_Transport_UDPv6_string_to_address_cEA(
    NDDS_Transport_Plugin    *self,
    NDDS_Transport_Address_t *address_out,
    const char               *address_in);

extern NDDS_Transport_DllExport 
RTI_INT32 NDDS_Transport_UDPv6_get_receive_interfaces_cEA(
    NDDS_Transport_Plugin      *self,
    RTI_INT32                  *found_more_than_provided_for_out,
    RTI_INT32                  *interface_reported_count_out,
    NDDS_Transport_Interface_t  interface_array_inout[],
    RTI_INT32                   interface_array_size_in);

extern NDDS_Transport_DllExport 
RTIBool NDDS_Transport_UDPv6_is_loopback_interface_ignored(
    const NDDS_Transport_Plugin * self);


#define NDDS_TRANSPORT_UDPV6_ADDRESS_LOCALHOST {{0,0,0,0, 0,0,0,0, 0,0,0,0, 0,0,0,1}}


#define NDDS_TRANSPORT_UDPV6_UNBLOCK_MESSAGE_SIZE \
    (4 + 4 + sizeof(NDDS_Transport_Plugin *))


#ifdef __cplusplus
    }   /* extern "C" */
#endif

  #include "transport/transport_udpv6_impl.h"

#endif /* transport_udpv6_h */
