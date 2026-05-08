package com.jobportal.config;

import com.jobportal.model.Job;
import com.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

        @Autowired
        private JobRepository jobRepository;

        @Override
        public void run(String... args) throws Exception {
                System.out.println("=================================================");
                System.out.println("☢️ NUCLEAR DATA INITIALIZER STARTING... ☢️");
                System.out.println("=================================================");

                // ONLY seed if the database is empty
                if (jobRepository.count() == 0) {
                        System.out.println("DEBUG: Database is empty. Seeding initial data...");

                        List<Job> jobsToSeed = Arrays.asList(
                                        // 1. Marketing
                                        new Job("Marketing Specialist", "Ogilvy", "Dubai, UAE", "$60k - $90k",
                                                        "Full Time",
                                                        "[Category: Marketing]\n\nWe are seeking a creative Marketing Specialist to drive digital growth for global brands. You will be responsible for SEO optimization, content strategy, and executing multi-channel social media campaigns.\n\nTech Stack: Google Analytics, SEMrush, HubSpot.\nExperience: 3+ years in digital marketing agency environments."),
                                        new Job("Social Media Manager", "BuzzFeed", "New York, USA", "$70k - $100k",
                                                        "Full Time",
                                                        "[Category: Marketing]\n\nJoin our viral content team to manage high-growth social media presence. You will curate daily content, engage with millions of followers, and analyze audience metrics to drive reach.\n\nTech Stack: Sprout Social, Adobe Creative Suite, Meta Business Suite.\nExperience: 2-4 years managing large-scale brand accounts."),
                                        new Job("Brand Strategist", "Nike", "Oregon, USA", "$90k - $120k", "Full Time",
                                                        "[Category: Marketing]\n\nLead the development of Nike's next iconic brand campaign. This role involves deep market research, consumer psychology analysis, and long-term positioning strategies for sports apparel.\n\nTech Stack: Nielsen Research, PowerPoint (Expert), Brandwatch.\nExperience: 5+ years in senior brand management or strategic planning."),
                                        new Job("Content Creator", "Netflix", "Los Angeles, USA", "$80k - $110k",
                                                        "Part Time",
                                                        "[Category: Marketing]\n\nHelp us tell stories that captivate the world. You'll produce professional-grade video trailers and written synopses for upcoming streaming releases across global markets.\n\nTech Stack: Premiere Pro, After Effects, Final Cut Pro.\nExperience: 2+ years in high-end video production or creative writing."),

                                        // 2. Customer Service
                                        new Job("Support Specialist", "Amazon", "Hyderabad, India", "₹5L - ₹8L",
                                                        "Full Time",
                                                        "[Category: Customer Service]\n\nProvide world-class technical support to Amazon customers. You'll resolve complex order issues, assist with platform navigation, and maintain high CSAT scores through effective communication.\n\nTech Stack: Zendesk, AWS Connect, Salesforce Service Cloud.\nExperience: 1-2 years in international voice or chat support."),
                                        new Job("Customer Success Manager", "Salesforce", "San Francisco, USA",
                                                        "$110k - $140k",
                                                        "Full Time",
                                                        "[Category: Customer Service]\n\nAct as the primary advocate for our enterprise clients. You will drive product adoption, manage renewals, and ensure long-term value realization through customized success plans.\n\nTech Stack: Salesforce CRM, Gainsight, Slack.\nExperience: 5+ years in B2B SaaS account management."),
                                        new Job("Call Center Associate", "Airtel", "Gurgaon, India", "₹3L - ₹5L",
                                                        "Part Time",
                                                        "[Category: Customer Service]\n\nJoin India's leading telecom provider to handle network-related customer queries. You will troubleshoot connectivity issues and provide plan recommendations to retail subscribers.\n\nTech Stack: CRM Dynamics, Avaya Telephony, In-house Knowledge Base.\nExperience: Freshers or 0-1 year experience with excellent communication skills."),
                                        new Job("Lead Support Engineer", "Microsoft", "Bengaluru, India", "₹15L - ₹25L",
                                                        "Full Time",
                                                        "[Category: Customer Service]\n\nLead a high-performing team of cloud support engineers. You'll oversee incident response for Azure services, manage critical escalations, and develop team technical competencies.\n\nTech Stack: Azure Monitor, Kusto Query Language (KQL), PowerShell.\nExperience: 8+ years in cloud infrastructure support and team leadership."),

                                        // 3. Human Resource
                                        new Job("HR Manager", "TCS", "Mumbai, India", "₹12L - ₹18L", "Full Time",
                                                        "[Category: Human Resource]\n\nDrive organizational excellence at TCS. This role involves managing end-to-end recruitment, designing employee engagement programs, and ensuring compliance with labor laws across domains.\n\nTech Stack: SAP SuccessFactors, Microsoft Office 365, iCIMS.\nExperience: 6+ years in corporate HR with a focus on talent management."),
                                        new Job("Technical Recruiter", "Google", "Mountain View, USA", "$120k - $160k",
                                                        "Full Time",
                                                        "[Category: Human Resource]\n\nFind the engineers who build the future. You will source, interview, and close top-tier software talent for Google's most critical infrastructure and AI projects.\n\nTech Stack: LinkedIn Recruiter, Workday, Google Workspace.\nExperience: 4-6 years in full-cycle tech recruiting at scale."),
                                        new Job("HR Coordinator", "Wipro", "Chennai, India", "₹6L - ₹9L", "Full Time",
                                                        "[Category: Human Resource]\n\nSupport our regional HR operations through efficient interview coordination and onboarding management. You will serve as the first point of contact for new joiners at Wipro.\n\nTech Stack: Oracle HCM, MS Excel, Outlook Scheduling.\nExperience: 2+ years in HR administration or operation coordination."),
                                        new Job("Diversity & Inclusion Lead", "Meta", "London, UK", "£80k - £110k",
                                                        "Full Time",
                                                        "[Category: Human Resource]\n\nSpearhead Meta's goal to build a more inclusive workplace. You'll design diversity metrics, lead unconscious bias training, and partner with ERGs to drive systemic change.\n\nTech Stack: Data Studio, SurveyMonkey, Workplace by Meta.\nExperience: 5+ years specifically in DEI leadership and cultural transformation."),

                                        // 4. Project Management
                                        new Job("Project Coordinator", "Infosys", "Pune, India", "₹8L - ₹12L",
                                                        "Full Time",
                                                        "[Category: Project Management]\n\nManage the gears of IT infrastructure projects. You will be responsible for sprint planning, resource allocation tracking, and maintaining project documentation for global clients.\n\nTech Stack: Jira, Confluence, Microsoft Project.\nExperience: 2-3 years in IT project coordination or PMO roles."),
                                        new Job("Senior Project Manager", "IBM", "Armonk, USA", "$140k - $180k",
                                                        "Full Time",
                                                        "[Category: Project Management]\n\nLead multi-million dollar digital transformation initiatives for IBM's Fortune 500 partners. You'll manage global cross-functional teams and ensure on-time delivery of complex software.\n\nTech Stack: IBM Rational, MS Project, Trello.\nExperience: 10+ years in senior PM roles with PMP or PRINCE2 certification."),
                                        new Job("Agile Coach", "Spotify", "Stockholm, Sweden", "€90k - €120k",
                                                        "Full Time",
                                                        "[Category: Project Management]\n\nEmbed the Spotify Model within our squads. You'll facilitate Scrum ceremonies, coach leadership on Agile mindset, and remove systemic blockers to build autonomous, high-velocity teams.\n\nTech Stack: Jira Align, Miro, Slack.\nExperience: 5+ years as a dedicated Scrum Master or Agile Coach."),
                                        new Job("Construction Project Manager", "L&T", "Mumbai, India", "₹15L - ₹20L",
                                                        "Full Time",
                                                        "[Category: Project Management]\n\nDirect the construction of landmark infrastructure. You will manage site safety, vendor relationships, and tight engineering deadlines for large-scale urban development projects.\n\nTech Stack: AutoCAD, Primavera P6, BIM 360.\nExperience: 7-10 years in civil engineering project management."),

                                        // 5. Business Development
                                        new Job("Business Analyst", "Morgan Stanley", "Bengaluru, India", "₹10L - ₹15L",
                                                        "Full Time",
                                                        "[Category: Business Development]\n\nBridging the gap between business and technology in investment banking. You'll analyze financial workflows, gather technical requirements, and present data-driven optimization strategies.\n\nTech Stack: SQL, Tableau, Power BI, Advanced Excel.\nExperience: 3-5 years in financial services business analysis."),
                                        new Job("Sales Director", "Oracle", "Austin, USA", "$180k - $250k", "Full Time",
                                                        "[Category: Business Development]\n\nDrive massive revenue growth for Oracle Cloud. You will lead a regional team of account executives, close seven-figure enterprise deals, and set the strategic sales roadmap.\n\nTech Stack: Oracle CX, Gong.io, Sales Navigator.\nExperience: 12+ years in enterprise software sales leadership."),
                                        new Job("Partnership Manager", "Uber", "Amsterdam, NL", "€70k - €100k",
                                                        "Full Time",
                                                        "[Category: Business Development]\n\nScale Uber's ecosystem by building strategic alliances with transit authorities and major merchants across Europe. You'll negotiate complex deals to increase platform utility.\n\nTech Stack: Salesforce, G-Suite, Looker.\nExperience: 4-7 years in strategic partnerships or corporate development."),
                                        new Job("Operations Lead", "Zomato", "Gurgaon, India", "₹14L - ₹22L",
                                                        "Full Time",
                                                        "[Category: Business Development]\n\nOptimize the backbone of India's leading food delivery app. You'll manage driver logistics, delivery efficiency metrics, and hyper-local supply chain operations.\n\nTech Stack: Python (Basic), SQL, Internal Ops Dashboard.\nExperience: 5+ years in logistics, food-tech operations, or supply chain management."),

                                        // 6. Sales & Communication
                                        new Job("Sales Representative", "Dell", "Chennai, India", "₹5L - ₹9L",
                                                        "Full Time",
                                                        "[Category: Sales & Communication]\n\nRepresent Dell's hardware portfolio to corporate and SME clients. You'll be responsible for the full sales cycle, from prospecting to closing and post-sales support.\n\nTech Stack: Salesforce, ZoomInfo, Microsoft Teams.\nExperience: 1-3 years in B2B hardware or SaaS sales."),
                                        new Job("PR Specialist", "Tesla", "Palo Alto, USA", "$100k - $140k",
                                                        "Full Time",
                                                        "[Category: Sales & Communication]\n\nShape the narrative for the future of sustainable energy. You'll manage high-stakes media relations, draft press releases, and coordinate global product announcements.\n\nTech Stack: Cision, Muck Rack, Twitter/X Media Studio.\nExperience: 5+ years in corporate PR or high-growth tech communications."),
                                        new Job("Customer Relations Officer", "HDFC Bank", "New Delhi, India",
                                                        "₹7L - ₹11L",
                                                        "Full Time",
                                                        "[Category: Sales & Communication]\n\nBuild and nurture relationships with high-net-worth individuals. You'll provide personalized financial advice and ensure priority banking satisfaction for HDFC clients.\n\nTech Stack: Finacle Core Banking, Microsoft Outlook, CRM.\nExperience: 3+ years in retail banking or relationship management."),
                                        new Job("Communications Consultant", "United Nations", "Geneva, Switzerland",
                                                        "$90k - $130k", "Contract",
                                                        "[Category: Sales & Communication]\n\nDevelop and execute global awareness campaigns for humanitarian causes. You'll manage multilingual content strategy and engage with international press bureaus.\n\nTech Stack: Mailchimp, Canva, Adobe Creative Suite.\nExperience: 7+ years in international non-profit communications."),

                                        // 7. Teaching & Education
                                        new Job("Mathematics Teacher", "Khan Academy", "Remote", "$60k - $80k",
                                                        "Full Time",
                                                        "[Category: Teaching & Education]\n\nCreate the tools that teach millions. You'll design interactive math curricula, record educational video content, and develop assessment algorithms for k-12 learners.\n\nTech Stack: LaTeX, Camtasia, Python (Scripting).\nExperience: 3+ years in classroom teaching or EdTech content development."),
                                        new Job("University Professor", "Stanford University", "Stanford, USA",
                                                        "$150k - $220k",
                                                        "Full Time",
                                                        "[Category: Teaching & Education]\n\nJoin the world-class CS faculty at Stanford. You will conduct cutting-edge AI research, publish in top venues, and teach graduate-level courses in machine learning.\n\nTech Stack: PyTorch, TensorFlow, Google Cloud AI.\nExperience: PhD in Computer Science and a strong portfolio of academic research."),
                                        new Job("Language Instructor", "Duolingo", "Pittsburgh, USA", "$70k - $90k",
                                                        "Full Time",
                                                        "[Category: Teaching & Education]\n\nMaster of linguistics wanted! You'll design pedagogical structures for our English and Spanish courses, ensuring a balance of gamification and effective learning.\n\nTech Stack: Figma, In-house CMS, Python.\nExperience: 4+ years in TESOL or Spanish language instruction and curriculum design."),
                                        new Job("Corporate Trainer", "Accenture", "Hyderabad, India", "₹9L - ₹14L",
                                                        "Full Time",
                                                        "[Category: Teaching & Education]\n\nUpskill the global workforce. You'll design and lead workshops on soft skills, leadership development, and proprietary software tools for Accenture employees.\n\nTech Stack: Microsoft Teams, Mural, Articulate Storyline.\nExperience: 5+ years in professional corporate training or OD consulting."),

                                        // 8. Design & Creative
                                        new Job("Senior UI/UX Designer", "Adobe", "San Jose, USA", "$130k - $170k",
                                                        "Full Time",
                                                        "[Category: Design & Creative]\n\nDesign the tools that designers use. You'll work on the core interface for Adobe's Creative Cloud suite, focusing on seamless workflows and modern aesthetic standards.\n\nTech Stack: Figma, Adobe XD, Illustrator, After Effects.\nExperience: 6+ years in product design with a strong SaaS portfolio."),
                                        new Job("Motion Graphics Artist", "Disney", "Burbank, USA", "$90k - $130k",
                                                        "Full Time",
                                                        "[Category: Design & Creative]\n\nBring Disney stories to life with stunning motion graphics. You'll create animations for marketing trailers, digital billboards, and feature film title sequences.\n\nTech Stack: After Effects, Cinema 4D, Maya.\nExperience: 4+ years in motion design or broadcast animation."),
                                        new Job("Graphic Designer", "Canva", "Sydney, Australia", "$80k - $110k",
                                                        "Full Time",
                                                        "[Category: Design & Creative]\n\nDemocratize design by building premium templates for millions of Canva users. You'll focus on trending visual styles and intuitive layout accessibility.\n\nTech Stack: Canva (Expert), Adobe Illustrator, InDesign.\nExperience: 3-5 years in agency or in-house graphic design."),
                                        new Job("Creative Director", "Apple", "Cupertino, USA", "$200k - $300k",
                                                        "Full Time",
                                                        "[Category: Design & Creative]\n\nProtect and evolve the world's most recognizable brand aesthetic. You will lead a global team of designers to create perfect visual marketing across all Apple products.\n\nTech Stack: Apple Design System, Keynote, Photoshop.\nExperience: 12+ years in creative leadership at a top-tier brand or agency."),

                                        // 9. Software Development (Matching the form)
                                        new Job("Java Backend Developer", "Oracle", "Bengaluru, India", "₹18L - ₹30L",
                                                        "Full Time",
                                                        "[Category: Software Development]\n\nBuild the high-performance backend for Oracle's cloud services. You'll develop scalable microservices using Spring Boot, optimize SQL queries, and ensure mission-critical uptime.\n\nTech Stack: Java 17, Spring Boot, Hibernate, Oracle DB, Docker.\nExperience: 4-7 years in enterprise Java backend development."),
                                        new Job("Senior Python Engineer", "Instagram", "San Francisco, USA",
                                                        "$160k - $220k",
                                                        "Full Time",
                                                        "[Category: Software Development]\n\nArchitect the microservices that power Instagram for billions. You'll focus on high-throughput Python systems, database sharding, and real-time feed optimization.\n\nTech Stack: Python, Django, Redis, Cassandra, Kubernetes.\nExperience: 6+ years specializing in distributed systems and backends at scale."),
                                        new Job("Frontend Architect", "React Corp", "Remote", "$140k - $190k",
                                                        "Full Time",
                                                        "[Category: Software Development]\n\nSet the frontend standard for our core UI framework. You'll build reusable component libraries, optimize web performance, and lead the transition to modern React patterns.\n\nTech Stack: React, Next.js, Redux, Tailwind CSS, Jest.\nExperience: 8+ years with deep expertise in modern JavaScript ecosystems."),
                                        new Job("Full Stack Developer", "Stripe", "Dublin, Ireland", "€80k - €120k",
                                                        "Full Time",
                                                        "[Category: Software Development]\n\nBuild the future of financial infrastructure. You will work across the full stack to design seamless checkout experiences and robust billing APIs for global businesses.\n\nTech Stack: Ruby on Rails, React, Go, PostgreSQL.\nExperience: 5+ years in full-stack product engineering and API design."),

                                        // 10. Additional Part Time Jobs (Adding 4 more for a total of 6)
                                        new Job("Freelance Graphic Designer", "Creative Agency", "Mumbai, India",
                                                        "₹20k - ₹35k",
                                                        "Part Time",
                                                        "[Category: Design & Creative]\n\nProvide high-quality visual assets for our seasonal marketing campaigns. You'll work on social media post designs, email headers, and small brand updates on a per-project basis.\n\nTech Stack: Photoshop, Canva, Figma.\nExperience: 2+ years of freelance or agency experience with a fresh portfolio."),
                                        new Job("Online Math Tutor", "Education First", "Remote", "$25 - $40 /hr",
                                                        "Part Time",
                                                        "[Category: Teaching & Education]\n\nGuide high school students through the complexities of calculus and SAT/ACT prep. You'll lead 1-on-1 online sessions and provide detailed performance feedback.\n\nTech Stack: Zoom, Whiteboard.fi, Google Classroom.\nExperience: Strong mathematical background (STEM degree) and previous tutoring experience."),
                                        new Job("Retail Associate", "H&M", "New Delhi, India", "₹15k - ₹25k",
                                                        "Part Time",
                                                        "[Category: Business Development]\n\nBe the face of H&M. You'll assist customers with product selection, manage window displays, and support the checkout process during peak evening and weekend hours.\n\nTech Stack: Retail POS, Inventory Manager.\nExperience: Freshers or students with great energy and customer service skills."),
                                        new Job("Data Entry Clerk", "Tech Solutions", "Hyderabad, India", "₹12k - ₹20k",
                                                        "Part Time",
                                                        "[Category: Business Development]\n\nEnsure our client databases are accurate and up-to-date. This role requires high attention to detail and fast typing speeds for processing large volumes of business records.\n\nTech Stack: MS Excel, Google Sheets, Data Verification Tools.\nExperience: 1+ year in data entry or admin roles with 50+ WPM typing speed."),

                                        // 11. Additional Full Time Jobs
                                        new Job("SEO Specialist", "Google", "Gurgaon, India", "₹10L - ₹15L",
                                                        "Full Time",
                                                        "[Category: Marketing]\n\nHelp our users find what they need. You will optimize landing pages for Google's internal products, perform deep keyword research, and track search ecosystem changes globally.\n\nTech Stack: Search Console, Analytics, Screaming Frog.\nExperience: 4+ years of proven success in enterprise-level SEO."),
                                        new Job("Node.js Developer", "Uber", "Bengaluru, India", "₹20L - ₹35L",
                                                        "Full Time",
                                                        "[Category: Software Development]\n\nBuild real-time, event-driven services for Uber's driver matching platform. You'll focus on high-concurrency Node.js applications and low-latency API development.\n\nTech Stack: Node.js, TypeScript, Kafka, MongoDB.\nExperience: 5+ years with heavy focus on scalable Node.js microservices."));

                        jobRepository.saveAllAndFlush(jobsToSeed);

                        System.out.println("DEBUG: Seeding complete. New count: " + jobRepository.count());
                        System.out.println("=================================================");
                        System.out.println("☢️ DATABASE SEEDING SUCCESSFUL ☢️");
                        System.out.println("=================================================");
                } else {
                        System.out.println("DEBUG: Database already contains data. Skipping seeding.");
                }
        }
}
