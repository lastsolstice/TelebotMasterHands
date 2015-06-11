/* (c) Copyright, Real-Time Innovations, Inc. 2003-2009. All rights reserved. */
/*
 * @(#)ndds_writerhistory_cpp.h    generated by: makeheader    Sat Nov 23 02:40:02 2013
 *
 *		built from:	ndds_writerhistory_cpp.ifcxx
 */

#ifndef ndds_writerhistory_cpp_h
#define ndds_writerhistory_cpp_h


    #ifndef dds_cpp_dll_h
      #include "dds_cpp/dds_cpp_dll.h"
    #endif
    #ifndef ndds_writerhistory_c_h
      #include "ndds/ndds_writerhistory_c.h"
    #endif
    #ifndef dds_cpp_domain_h
      #include "dds_cpp/dds_cpp_domain.h"
    #endif

class DDSCPPDllExport NDDSWriterHistoryPluginSupport {
  public:
    /*i \dref_WriterHistory_PluginSupport_register_plugin */
    static DDS_Boolean register_plugin(
        DDSDomainParticipant* participant_in,
        struct NDDS_WriterHistory_Plugin * wh_plugin_in,
        const char * name_in);

    /*i \dref_WriterHistory_PluginSupport_lookup_plugin */
    static struct NDDS_WriterHistory_Plugin * lookup_plugin(
        DDSDomainParticipant* participant_in,
        const char * name_in);
};

#endif /* ndds_writerhistory_cpp_h */
