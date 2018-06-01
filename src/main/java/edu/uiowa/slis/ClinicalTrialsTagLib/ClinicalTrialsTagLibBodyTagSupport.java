package edu.uiowa.slis.ClinicalTrialsTagLib;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@SuppressWarnings("serial")
public class ClinicalTrialsTagLibBodyTagSupport extends BodyTagSupport {

    protected DataSource theDataSource = null;
    protected Connection theConnection = null;

    public ClinicalTrialsTagLibBodyTagSupport() {
        super();
    }

    private static final Log log = LogFactory.getLog(ClinicalTrialsTagLibBodyTagSupport.class);

    @Override
    public int doEndTag() throws JspException {
    	freeConnection();
    	return super.doEndTag();
    }
    
    public DataSource getDataSource() {
        if (theDataSource == null) try {
            theDataSource = (DataSource)new InitialContext().lookup("java:/comp/env/jdbc/ClinicalTrialsTagLib");
        } catch (Exception e) {
            log.error("Error in database initialization", e);
        }
 
        return theDataSource;
    }
    
    public Connection getConnection() throws SQLException {
        if (theConnection == null)
        	theConnection = getDataSource().getConnection();
        return theConnection;
    }
    
    public void freeConnection() throws JspTagException {
     try {
        if (theConnection != null)
        	theConnection.close();
        theConnection = null;
     } catch (SQLException e) {
         log.error("JDBC error freeing connection", e);
        theConnection = null;
         throw new JspTagException("Error: JDBC error freeing connection");
     }
    }

}