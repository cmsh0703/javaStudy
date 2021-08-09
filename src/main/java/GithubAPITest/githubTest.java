package GithubAPITest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public class githubTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.out.println("test");
		// properties ���Ϸ� token ����---> token destroy �Ǹ� github server�� ����ȵǼ� ���� �����ؼ� �ٲ������
		String path = "src/main/resources/application.properties";
		//GitHub github = GitHubBuilder.fromPropertyFile(path).build();
		// �Ʒ������ �����ϱ� �ϳ�, ����
		 GitHub github = new GitHubBuilder().withOAuthToken("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").build();
		github.checkApiUrlValidity();

		// �ش� repository ��������
		GHRepository repository = github.getRepository("cmsh0703/javaStudy");

		// ��� issue ��ü ��������
		List<GHIssue> issues = repository.getIssues(GHIssueState.ALL);

		//HashMap<String, Integer> hm = new HashMap<String, Integer>();
		HashMap hm = new HashMap();
		
		for(int i= 0; i < issues.size(); i++) {
			System.out.println("["+(i+1)+" ��° issue] ===== "+issues.get(i));
			//issue���� comment ��������
			List<GHIssueComment> issueComment = issues.get(i).getComments();
			
			for(int j = 0; j < issueComment.size(); j++) {
				GHIssueComment commentTmp = issueComment.get(j);
				String userName = commentTmp.getUserName();
				String content = commentTmp.getBody();
				System.out.print("userName : "+userName+", content : "+content);
				
				if(hm.containsKey(userName)){
					hm.put(userName, Integer.parseInt(hm.get(userName).toString())+1);
					System.out.println("������ ����");
				}else {
					hm.put(userName, 1);
					System.out.println("ù���");
				}
			}
			System.out.println();
		}
		
		Iterator st = hm.keySet().iterator();
		for(;st.hasNext();) {
			String name = st.next().toString();
			System.out.println("����� "+name+"�� ������ : "+(Integer.parseInt(hm.get(name).toString())/(issues.size()*1.00))*100+" %");
		}
		
	}

}
