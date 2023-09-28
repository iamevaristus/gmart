package g.store.services;

import g.store.exception.AuthorizationException;
import g.store.model.others.Feedback;
import g.store.types.AuthenticationTypes;
import g.store.types.ComplaintStatus;

public interface StoreFeedbackService {

    public AuthenticationTypes giveFeedback(Feedback complaint);

    public ComplaintStatus resolveComplaints(Feedback complaint) throws AuthorizationException;

    public ComplaintStatus escalateComplaints(Feedback complaint) throws AuthorizationException;

    public ComplaintStatus pendComplaints(Feedback complaint) throws AuthorizationException;
}
