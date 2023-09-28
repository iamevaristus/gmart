//package g.store.services;
//
//import g.store.exception.AuthorizationException;
//import g.store.model.others.Feedback;
//import g.store.enums.auth.ActionStatus;
//import g.store.enums.others.ComplaintStatus;
//
//public interface FeedbackService {
//
//    /**
//     * This gives feedback/complaint to the Store.
//     * @param complaint The complaint or the feedback/complaint that is to be given,
//     * @return AuthenticationStatus
//     */
//    public ActionStatus giveFeedback(Feedback complaint);
//
//    /**
//     * This resolves feedback/complaint to the Store.
//     * @param complaint The complaint or the feedback/complaint that is to be resolved,
//     * @return ComplaintStatus
//     */
//    public ComplaintStatus resolveComplaints(Feedback complaint) throws AuthorizationException;
//
//    /**
//     * This escalates the complaints.
//     * @param complaint The complaint or the feedback/complaint that is to be resolved,
//     * @return ComplaintStatus
//     * @throws AuthorizationException error.
//     */
//    public ComplaintStatus escalateComplaints(Feedback complaint) throws AuthorizationException;
//
//    /**
//     * This pends feedback/complaint to the Store.
//     * @param complaint The complaint or the feedback/complaint that is to be resolved,
//     * @return ComplaintStatus
//     */
//    public ComplaintStatus pendComplaints(Feedback complaint) throws AuthorizationException;
//}
