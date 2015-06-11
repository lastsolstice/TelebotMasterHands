/* (c) Copyright, Real-Time Innovations, Inc. 2003-2009. All rights reserved. */
/*
 * @(#)connext_c_requester.h    generated by: makeheader    Sat Nov 23 02:41:06 2013
 *
 *		built from:	requester.ifc
 */

#ifndef connext_c_requester_h
#define connext_c_requester_h



#ifndef ndds_c_h
  #include "ndds/ndds_c.h"
#endif

#ifndef connext_cpp_dll_h
  #include "connext_c/connext_c_dll.h"
#endif

#define NDDSUSERDllExport


struct RTI_Connext_EntityUntypedImpl;

typedef struct RTI_Connext_EntityUntypedImpl RTI_Connext_RequesterUntypedImpl;

struct RTI_Connext_Requester {
    RTI_Connext_RequesterUntypedImpl * _impl;
};

typedef struct RTI_Connext_Requester RTI_Connext_Requester;

typedef struct RTI_Connext_RequesterParams {

    /**
     * \dref_RequesterParams_participant
     */
    DDS_DomainParticipant * participant;

    /**
     * \dref_RequesterParams_service_name
     */
    char* service_name;

    /**
     * \dref_RequesterParams_request_topic_name
     */
    char* request_topic_name;

    /**
     * \dref_RequesterParams_reply_topic_name
     */
    char* reply_topic_name;

    /**
     * \dref_RequesterParams_qos_library_name
     */
    char* qos_library_name;

    /**
     * \dref_RequesterParams_qos_profile
     */
    char* qos_profile_name;

    /**
     * \dref_RequesterParams_datawriter_qos
     */
    const struct DDS_DataWriterQos * datawriter_qos;

    /**
     * \dref_RequesterParams_datareader_qos
     */
    const struct DDS_DataReaderQos * datareader_qos;

    /**
     * \dref_RequesterParams_publisher
     */
    DDS_Publisher * publisher;

    /**
     * \dref_RequesterParams_subscriber
     */
    DDS_Subscriber * subscriber;
} RTI_Connext_RequesterParams;


#define RTI_Connext_RequesterParams_INITIALIZER { \
    NULL, \
    NULL, \
    NULL, \
    NULL, \
    NULL, \
    NULL, \
    NULL, \
    NULL, \
    NULL, \
    NULL  \
}

extern XMQCDllExport
DDS_ReturnCode_t RTI_Connext_Requester_delete(RTI_Connext_Requester * self);

extern XMQCDllExport
DDS_ReturnCode_t RTI_Connext_Requester_wait_for_replies(
    RTI_Connext_Requester * self,
    DDS_Long min_count,
    const struct DDS_Duration_t * max_wait);

extern XMQCDllExport
DDS_ReturnCode_t RTI_Connext_Requester_wait_for_replies_for_related_request(
    RTI_Connext_Requester * self,
    DDS_Long min_count,
    const struct DDS_Duration_t * max_wait,
    const struct DDS_SampleIdentity_t * related_request_id);


#define RTI_CONNEXT_REQUESTER_DECL(TReq, TRep, TRequester)                    \
                                                                              \
    typedef struct TRequester {                                               \
        RTI_Connext_Requester parent;                                         \
    } TRequester;                                                             \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    TRequester* TRequester ## _create(                                        \
        DDS_DomainParticipant * participant, const char* service_name);       \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    TRequester* TRequester ## _create_w_params(                               \
        const RTI_Connext_RequesterParams* params);                           \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    DDS_ReturnCode_t TRequester ## _send_request(                             \
        TRequester* self, const TReq* request);                               \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    DDS_ReturnCode_t TRequester ## _send_request_w_params(                    \
        TRequester* self, const TReq* request,                                \
        struct DDS_WriteParams_t* request_info);                              \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    DDS_ReturnCode_t TRequester ## _receive_reply(                            \
        TRequester* self, TRep* reply,                                        \
        struct DDS_SampleInfo* sample_info,                                   \
        const struct DDS_Duration_t* timeout);                                \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    DDS_ReturnCode_t TRequester ## _receive_replies(                          \
        TRequester* self, struct TRep ## Seq* received_data,                  \
        struct DDS_SampleInfoSeq* info_seq, DDS_Long min_reply_count,         \
        DDS_Long max_reply_count, const struct DDS_Duration_t* max_wait);     \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    DDS_ReturnCode_t TRequester ## _take_reply(                               \
        TRequester* self, TRep* reply,                                        \
        struct DDS_SampleInfo* sample_info);                                  \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    DDS_ReturnCode_t TRequester ## _take_replies(                             \
        TRequester* self, struct TRep ## Seq* reply_seq,                      \
        struct DDS_SampleInfoSeq* sample_info_seq, DDS_Long max_count);       \
                                                                              \
    NDDSUSERDllExport XMQCDllExport DDS_ReturnCode_t                          \
    TRequester ## _take_reply_for_related_request(                            \
        TRequester* self, TRep* reply,                                        \
        struct DDS_SampleInfo* sample_info,                                   \
        const struct DDS_SampleIdentity_t* related_request_info);             \
                                                                              \
    NDDSUSERDllExport XMQCDllExport DDS_ReturnCode_t                          \
    TRequester ## _take_replies_for_related_request(                          \
        TRequester* self, struct TRep ## Seq* reply_seq,                      \
        struct DDS_SampleInfoSeq* sample_info_seq, DDS_Long max_count,        \
        const struct DDS_SampleIdentity_t* related_request_info);             \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    DDS_ReturnCode_t TRequester ## _read_reply(                               \
        TRequester* self, TRep* reply,                                        \
        struct DDS_SampleInfo* sample_info);                                  \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    DDS_ReturnCode_t TRequester ## _read_replies(                             \
        TRequester* self, struct TRep ## Seq* reply_seq,                      \
        struct DDS_SampleInfoSeq* sample_info_seq, DDS_Long max_count);       \
                                                                              \
    NDDSUSERDllExport XMQCDllExport DDS_ReturnCode_t                          \
    TRequester ## _read_reply_for_related_request(                            \
        TRequester* self, TRep* reply,                                        \
        struct DDS_SampleInfo* sample_info,                                   \
        const struct DDS_SampleIdentity_t* related_request_info);             \
                                                                              \
    NDDSUSERDllExport XMQCDllExport DDS_ReturnCode_t                          \
    TRequester ## _read_replies_for_related_request(                          \
        TRequester* self, struct TRep ## Seq* reply_seq,                      \
        struct DDS_SampleInfoSeq* sample_info_seq, DDS_Long max_count,        \
        const struct DDS_SampleIdentity_t* related_request_info);             \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    TReq ## DataWriter* TRequester ## _get_request_datawriter(                \
        TRequester* self);                                                    \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    TRep ## DataReader* TRequester ## _get_reply_datareader(                  \
        TRequester* self);                                                    \
                                                                              \
    NDDSUSERDllExport XMQCDllExport                                           \
    DDS_ReturnCode_t TRequester_return_loan(                                  \
        TRequester* self, struct TRep ## Seq *received_data,                  \
        struct DDS_SampleInfoSeq *info_seq);                                  \


#endif /* connext_c_requester_h */
