package es.uem.cracking.master_soa.scheduler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 
 * Master SOA Scheduler Servlet
 * 
 * @author egrande
 *
 */
public class MasterSOASchedulerServlet extends HttpServlet  {

	// Private attributes
	
	private static final long serialVersionUID = -9181751954667379992L;
    private static final String GROUP_NAME = "MasterGroup";
    private static final String MASTER_TRIGGER_NAME = "MasterDispatcherTrigger";
    private static final String MASTER_JOB_NAME = "MasterDispatcherJob";

    private StdSchedulerFactory schedFact;
    private static Scheduler sched;
    
    
    // Public methods
    
    /**
     * Init servlet
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            schedFact = new StdSchedulerFactory("soa_quartz.properties");
            sched = schedFact.getScheduler();
            System.out.println(this.getClass().getName() + " started");
            sched.start();

            // Create Master Dispatch job
            JobDetail jd = new JobDetail(MASTER_JOB_NAME, GROUP_NAME, MasterSOAJob.class);
            int repeatInterval = Integer.parseInt(getInitParameter("TRIGGER_REPEATINTERVAL"));
            System.out.println(this.getClass().getName() + " Repeat interval for " + MASTER_JOB_NAME + ": " + repeatInterval + " milliseconds");
            SimpleTrigger simpleTrigger = new SimpleTrigger(MASTER_TRIGGER_NAME, GROUP_NAME, SimpleTrigger.REPEAT_INDEFINITELY, repeatInterval);
            System.out.println(this.getClass().getName() + " Scheduling Job " + MASTER_JOB_NAME);
            sched.scheduleJob(jd, simpleTrigger);
            System.out.println(this.getClass().getName() + " Job " + MASTER_JOB_NAME + " scheduled");

        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " " + e.getLocalizedMessage());
            e.printStackTrace(System.err);
        }
    }

    /**
     * Destroy servlet
     */
    public void destroy() {
        try {
            if (sched != null) {
                sched.unscheduleJob(MASTER_TRIGGER_NAME, GROUP_NAME);
                sched.shutdown();
            }
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " failed to shutdown: " + e.toString());
            e.printStackTrace(System.err);
        }
        System.out.println(this.getClass().getName() + " stopped");
    }

    
    /**
     * Process http GET
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter ajax = new PrintWriter(response.getOutputStream());

        String action = request.getParameter("action");
        if ("single".equals(action)) {
            if (sched != null) {
                try {
                    Trigger trigger1 = new SimpleTrigger("MasterSingleTrigger", GROUP_NAME, new Date());
                    trigger1.setJobName(MASTER_JOB_NAME);
                    trigger1.setJobGroup(GROUP_NAME);

                    // Schedule trigger
                    sched.scheduleJob(trigger1);

                } catch (Exception e) {
                    System.err.println(this.getClass().getName() + e.getLocalizedMessage());
                    e.printStackTrace(System.err);
                }
            }
        } else if ("start".equals(action)) {
            if (sched != null) {
                try {

                    // Create Master Dispatch job
                    JobDetail jd = new JobDetail(MASTER_JOB_NAME, GROUP_NAME, MasterSOAJob.class);
                    int repeatInterval = Integer.parseInt(getInitParameter("TRIGGER_REPEATINTERVAL"));
                    System.out.println(this.getClass().getName() + " Repeat interval for " + MASTER_JOB_NAME + ": " + repeatInterval + " milliseconds");
                    SimpleTrigger simpleTrigger = new SimpleTrigger(MASTER_TRIGGER_NAME, GROUP_NAME, SimpleTrigger.REPEAT_INDEFINITELY, repeatInterval);
                    System.out.println(this.getClass().getName() + " Scheduling Job " + MASTER_JOB_NAME);
                    sched.scheduleJob(jd, simpleTrigger);
                    System.out.println(this.getClass().getName() + " Job " + MASTER_JOB_NAME + " scheduled");

                } catch (Exception e) {
                	System.err.println(this.getClass().getName() + e.getLocalizedMessage());
                    e.printStackTrace(System.err);
                }
            }
        } else if ("stop".equals(action)) {
            if (sched != null) {
                try {
                	sched.unscheduleJob(MASTER_TRIGGER_NAME, GROUP_NAME);
                    System.out.println(this.getClass().getName() + " stopped");
                } catch (Exception e) {
                    System.err.println(this.getClass().getName() + " failed to shutdown: " + e.toString());
                    e.printStackTrace(System.err);
                }
            }
        }
	
	    
	    ajax.println("<html>");
	    ajax.println(" <head>");
	    ajax.println(" <meta charset=\"UTF-8\">");
	    ajax.println(" <title>pfm-mustic9 - Master SOA Scheduler Web Interface</title>");
	    ajax.println(" <link href=\"css/soa_sched.css\" rel=\"stylesheet\" type=\"text/css\">");
	    ajax.println(" <script type=\"text/javascript\" src=\"js/prototype.js\"></script>");
	    ajax.println(" <script type=\"text/javascript\">function startAjaxPeriodicalUpdater(){new Ajax.PeriodicalUpdater(document.body, document.URL, {method: 'get', frequency: 20, decay: 1});}</script>");
	    ajax.println(" <link rel=\"shortcut icon\" href=\"img/mustic9.jpg\" />");
	    ajax.println(" </head>");
	    ajax.println(" <body onload='startAjaxPeriodicalUpdater()'>");
	    ajax.println(" <h2 class=\"section-title\"><img src=\"img/mustic9.jpg\"/> Master SOA Scheduler @ " + System.getProperty("weblogic.Name") + " <img src=\"img/mustic9.jpg\"/></h2>");
	    ajax.println("<p/><p/>");
	    ajax.println("<table class=\"SchedulerDashboard\" align=\"center\">");
	    ajax.println("<tbody>");
	    ajax.println("<tr><td align=\"left\">Trigger name</td><td>Next time</td><td>Last time</td><td>Repeat interval in milliseconds</td></tr>");
	    
        String[] jobGroups;
        String[] jobsInGroup;
        Trigger[] jobTriggers;
        SimpleTrigger simpleTrigger;

        try {
            jobGroups = sched.getJobGroupNames();
            for (int i = 0; i < jobGroups.length; i++) {
                jobsInGroup = sched.getJobNames(jobGroups[i]);
                for (int j = 0; j < jobsInGroup.length; j++) {
                    jobTriggers = sched.getTriggersOfJob(jobsInGroup[j], jobGroups[i]);
                    for (int k = 0; k < jobTriggers.length; k++) {
                        if ("org.quartz.SimpleTrigger".equals(jobTriggers[k].getClass().getName())) {
                            simpleTrigger = (SimpleTrigger)jobTriggers[k];
                            ajax.printf("<tr><td align=\"left\">%s</td><td>%s</td><td>%s</td><td>%s milliseconds</td></tr>",
                            jobTriggers[k].getName(),
                            jobTriggers[k].getNextFireTime(),
                            jobTriggers[k].getPreviousFireTime(),
                            simpleTrigger.getRepeatInterval());
                        } else {
                            ajax.printf("<tr><td align=\"left\">%s</td><td>%s</td><td></td><td></td></tr>",
                            jobTriggers[k].getName(), jobTriggers[k].getNextFireTime());
                        }
                    }
                }
            }
    	
        } catch (Exception e) {
            System.err.println(this.getClass().getName() +" failed: " + e.toString());
            e.printStackTrace(System.err);
        }

        ajax.println("</tbody>");
        ajax.println("</table>");
        ajax.println("<footer id=\"main-footer\"><p>pfm-mustic9, Copyright (C) 2015 Elías Grande Rubio</p><p>pfm-mustic9 comes with ABSOLUTELY NO WARRANTY; for details type `show w'. This is free software, and you are welcome to redistribute it under certain conditions; type `show c' for details.</p></footer>");
	    ajax.println("</body>");
	    ajax.println("</html>");
        ajax.flush();
    }

}
